export default {
    fetchSubjects: (state, payload) => {
        state.subjects = payload
    },
    createSubject: (state, payload) => {
        payload.students = []
        state.subjects.push(payload)
    },
    updateSubject: (state, payload) => {
        let index = state.subjects.findIndex(subject => subject.id === payload.id)
        state.subjects.splice(index, 1, payload)
    },
    deleteSubject: (state, payload) => {
        let index = state.subjects.findIndex(subject => subject.id === payload.id)
        state.subjects.splice(index, 1)
    }
}