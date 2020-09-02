export default {
    subjects: state => state.subjects.sort((first, seocnd) => {
        return seocnd.students.length - first.students.length
    })
}