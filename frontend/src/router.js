import VueRouter from "vue-router"
import Student from "../views/Student";
import Subject from "../views/Subject";

let routes = [
    {
        path: '/students',
        name: 'students',
        component: Student,
        meta: {
            title: 'Học sinh'
        }
    },
    {
        path: '/subjects',
        name: 'subjects',
        component: Subject,
        meta: {
            title: 'Môn học'
        }
    }
];

const router = new VueRouter({mode: 'history', routes});
router.beforeEach((to, from, next) => {
    if (to.path === '/') {
        return next('students')
    }
    document.title = to.meta.title
    return next()
})
export default router
