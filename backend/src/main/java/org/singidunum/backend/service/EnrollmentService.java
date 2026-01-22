package org.singidunum.backend.service;

import org.singidunum.backend.model.Address;
import org.singidunum.backend.model.Role;
import org.singidunum.backend.model.Student;
import org.singidunum.backend.model.StudentOnYear;
import org.singidunum.backend.model.Subject;
import org.singidunum.backend.model.SubjectAttendance;
import org.singidunum.backend.model.SubjectRealisation;
import org.singidunum.backend.model.User;
import org.singidunum.backend.model.YearOfStudy;
import org.singidunum.backend.repository.AddressRepository;
import org.singidunum.backend.repository.RoleRepository;
import org.singidunum.backend.repository.StudentOnYearRepository;
import org.singidunum.backend.repository.StudentRepository;
import org.singidunum.backend.repository.SubjectAttendanceRepository;
import org.singidunum.backend.repository.UserRepository;
import org.singidunum.backend.repository.YearOfStudyRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;

    private final StudentOnYearRepository studentOnYearRepository;
    private final YearOfStudyRepository yearOfStudyRepository;
    private final RoleRepository roleRepository;
    private final SubjectAttendanceRepository subjectAttendanceRepository;

    public EnrollmentService(UserRepository userRepository,
                             StudentRepository studentRepository,
                             AddressRepository addressRepository,
                             StudentOnYearRepository studentOnYearRepository,
                             YearOfStudyRepository yearOfStudyRepository,
                             RoleRepository roleRepository,
                             SubjectAttendanceRepository subjectAttendanceRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.studentOnYearRepository = studentOnYearRepository;
        this.yearOfStudyRepository = yearOfStudyRepository;
        this.roleRepository = roleRepository;
        this.subjectAttendanceRepository = subjectAttendanceRepository;
    }

    @Transactional
    public void enrolStudent(String username,
                             Long yearOfStudyId,
                             String name,
                             String surname,
                             String pin,
                             Long addressId) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!user.getActive()) {
            throw new RuntimeException("User is inactive");
        }

        Student student = user.getStudent();
        if (student == null) {
            Address address = this.addressRepository.findById(addressId)
                    .orElseThrow(() -> new RuntimeException("Address not found"));

            student = new Student();
            student.setUser(user);
            student.setName(name);
            student.setSurname(surname);
            student.setPin(pin);
            student.setAddress(address);

            student = this.studentRepository.save(student);
            user.setStudent(student);
        }

        YearOfStudy yearOfStudy = this.yearOfStudyRepository.findById(yearOfStudyId)
                .orElseThrow(() -> new RuntimeException("Year of study not found"));

        Optional<StudentOnYear> existing = this.studentOnYearRepository
                .findByStudentIdAndYearOfStudyId(student.getId(), yearOfStudyId);

        if (existing.isPresent()) {
            throw new RuntimeException("Student is already enrolled on this year");
        }

        StudentOnYear studentOnYear = new StudentOnYear();
        studentOnYear.setStudent(student);
        studentOnYear.setYearOfStudy(yearOfStudy);
        studentOnYear.setEnrolmentDate(new Date());
        studentOnYear.setRecordNumber(resolveRecordNumberForEnrollment(user));

        this.studentOnYearRepository.save(studentOnYear);

        boolean hasStudentRole = user.getRoles().stream()
                .anyMatch(r -> "ROLE_STUDENT".equals(r.getName()));

        if (!hasStudentRole) {
            Role studentRole = this.roleRepository.findByName("ROLE_STUDENT")
                    .orElseThrow(() -> new RuntimeException("ROLE_STUDENT not found"));
            user.getRoles().add(studentRole);
        }

        this.userRepository.save(user);

        List<Subject> subjects = yearOfStudy.getSubjects();
        if (subjects != null && !subjects.isEmpty()) {
            for (Subject subject : subjects) {
                List<SubjectRealisation> subjectRealisations = subject.getSubjectRealisation();
                if (subjectRealisations != null && !subjectRealisations.isEmpty()) {
                    for (SubjectRealisation subjectRealisation : subjectRealisations) {
                        boolean attendanceExists = this.subjectAttendanceRepository
                                .existsByStudentIdAndSubjectRealisationId(student.getId(), subjectRealisation.getId());
                        
                        if (!attendanceExists) {
                            SubjectAttendance subjectAttendance = new SubjectAttendance();
                            subjectAttendance.setStudent(student);
                            subjectAttendance.setSubjectRealisation(subjectRealisation);
                            subjectAttendance.setFinalGrade(0);
                            
                            this.subjectAttendanceRepository.save(subjectAttendance);
                        }
                    }
                }
            }
        }
    }

    private String generateUniqueRecordNumber() {
        int year = LocalDate.now().getYear();
        for (int i = 0; i <= 999999; i++) {
            String candidate = String.format("%d%06d", year, i);
            if (!studentOnYearRepository.existsByRecordNumber(candidate)) {
                return candidate;
            }
        }
        throw new RuntimeException("No available record number for year " + year);
    }

    private String resolveRecordNumberForEnrollment(User user) {
        boolean hasOnlyRoleUser = user.getRoles() != null
                && user.getRoles().size() == 1
                && "ROLE_USER".equals(user.getRoles().get(0).getName());

        if (hasOnlyRoleUser) {
            return generateUniqueRecordNumber();
        }

        return studentOnYearRepository
                .findFirstByStudent_User_IdOrderByEnrolmentDateDesc(user.getId())
                .map(StudentOnYear::getRecordNumber)
                .filter(rn -> rn != null && !rn.isBlank())
                .orElseGet(this::generateUniqueRecordNumber);
    }
}
