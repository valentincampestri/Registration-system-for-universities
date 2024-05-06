# Registration System For Universities

REQUIREMENTS

Course Management:

• The system must store all available subjects for a degree program, including each course's name, workload, prerequisites, and subsequent courses.

• Each subject has a corresponding course, which includes information such as the assigned classroom, maximum classroom capacity, schedule, and assigned instructors.

• For each course, the system will store the time slot in which it is taught, which can be morning (7:45 to 11:45), afternoon (14:00 to 18:00), or evening (18:30 to 22:30).


Course Enrollment:

• Each student must be able to view all courses for a given subject to enroll.

• Upon enrollment, the system must validate that the student has passed the prerequisite courses.

• Enrollment for each subject is only enabled until the faculty's specified deadline.

• The system must validate that the number of courses a student enrolls in a semester does not exceed a maximum workload determined by the student's degree program.

• The system must redirect the student to Mercado Pago to pay the first installment for the enrolled courses.

• The system must be able to redirect the student to Mercado Pago, Pago Mis Cuentas, or Binance to pay the first installment for the enrolled courses.

• The system must allow students to search for courses by schedule and/or subject.


Faculty Management:

• Each teacher should be able to view the courses they are assigned to and the weekly course schedule.

• The system must generate a PDF or Excel report for each teacher, including the name, schedule, assigned classroom, and number of enrolled students for each course in a semester.

• Teachers can input their availability in terms of days and time slots.


Programming:

• The system must be able to generate schedules by assigning courses to teachers based on the scheduled time slot for each course and each teacher's preference.


Integration:

• The system must provide an interface to integrate the enrollment and faculty management modules with other administrative systems used by the university.

• The system must provide the number of hours assigned to each teacher monthly so that the human resources system can generate the corresponding payroll.

• The system must provide the number of enrolled students per course so that the academic management system can determine whether new courses need to be enabled.
