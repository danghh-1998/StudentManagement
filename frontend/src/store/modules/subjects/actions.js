import {api} from "@/api";

export default {
    fetchSubjects: context => {
        return api.get('subjects').then(response => {
            context.commit('fetchSubjects', response.data)
        })
    },
    createSubject: (context, payload) => {
        return api.post('subjects', payload.data).then(response => {
            context.commit('createSubject', response.data)
        })
    },
    updateSubject: (context, payload) => {
        return api.put(`subjects/${payload.id}`, payload.data).then(response => {
            context.commit('updateSubject', response.data)
        })
    },
    deleteSubject: (context, payload) => {
        return api.delete(`subjects/${payload.id}`).then(response => {
            context.commit('deleteSubject', response.data)
        })
    }
}