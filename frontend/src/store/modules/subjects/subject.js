import state from "@/store/modules/subjects/state";
import actions from "@/store/modules/subjects/actions";
import mutations from "@/store/modules/subjects/mutations";
import getters from "@/store/modules/subjects/getters";

export default {
    namespaced: true,
    state, actions, mutations, getters
}