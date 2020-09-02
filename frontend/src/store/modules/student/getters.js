export default {
    students: state => state.students,
    subjects: state => id => state.students.find(student => student.id === id).subjects
}