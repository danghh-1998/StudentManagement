import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

import student from "@/store/modules/student/student";
import subject from "@/store/modules/subjects/subject";

Vue.use(Vuex);

export const store = new Vuex.Store({
    modules: {
        student,
        subject
    },
    plugins: [createPersistedState({
        key: 'vuex'
    })],
});