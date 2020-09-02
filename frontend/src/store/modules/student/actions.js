import {api} from "@/api";

export default {
    fetchStudents: context => {
        return api.get(`students`).then((response) => {
            context.commit("fetchStudents", response.data)
        })
    },
    createStudent: (context, payload) => {
        return api.post('students', payload.data).then(response => {
            context.commit('createStudent', response.data)
        })
    },
    updateStudent: (context, payload)  => {
        return api.put(`students/${payload.id}`, payload.data).then(response => {
            context.commit('updateStudent', response.data)
        })
    },
    deleteStudent: (context, payload) => {
        return api.delete(`students/${payload.id}`).then(response => {
            context.commit('deleteStudent', response.data)
        })
    },
    registerStudent: (context, payload) => {
        return api.post(`students/${payload.id}/register/${payload.subjectId}`).then(response => {
            context.commit('registeredSubject', response.data)
        })
    },
    cancelregisterStudent: (context, payload) => {
        return api.put(`students/${payload.id}/register/${payload.subjectId}`).then(response => {
            context.commit('cancelRegisterStudent', response.data)
        })
    }
}