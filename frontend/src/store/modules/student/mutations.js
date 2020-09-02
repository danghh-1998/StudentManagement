export default {
    fetchStudents: (state, payload) => {
        state.students = payload;
    },
    createStudent: (state, payload) => {
        state.students.push(payload)
    },
    updateStudent: (state, payload) => {
        let index = state.students.findIndex(student => student.id === payload.id)
        state.students.splice(index, 1, payload)
    },
    deleteStudent: (state, payload) => {
        let index = state.students.findIndex(student => student.id === payload.id)
        state.students.splice(index, 1)
    },
    registeredSubject: (state, payload) => {
        let index = state.students.findIndex(student => student.id === payload.id)
        state.students.splice(index, 1, payload)
    },
    cancelRegisterStudent: (state, payload) => {
        let index = state.students.findIndex(student => student.id === payload.id)
        state.students.splice(index, 1, payload)
    }
}