<template>
    <div class="page">
        <app-nav-bar/>
        <div class="page-content">
            <div class="button-right">
                <b-button
                    class="button-right"
                    variant="primary"
                    @click="$bvModal.show('modal-create')"
                >Thêm môn học mới
                </b-button>
            </div>
            <SubjectCard v-for="subject in subjects" :key="subject.id" :subject="subject"/>
        </div>
        <b-modal
            id="modal-create"
            @ok="createSubject"
        >
            <template v-slot:modal-title>
                Thêm môn học mới
            </template>
            <b-form-group
                id="name"
                label="Tên môn học"
                label-for="form-name"
            >
                <b-form-input
                    id="form-name"
                    v-model="form.name"
                    required
                    placeholder="Nhập tên môn học"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                id="numLessons"
                label="Số tiết học"
                label-for="form-numLessons"
            >
                <b-form-input
                    id="form-numLessons"
                    v-model="form.numLessons"
                    required
                    placeholder="Số tiết học"
                ></b-form-input>
            </b-form-group>
            <label for="form-startDate">Ngày bắt đầu</label>
            <b-form-datepicker
                id="form-startDate"
                v-model="form.startDate"
                locale="vi"
                :max="form.endDate ? form.endDate : new Date()"
                placeholder="Ngày bắt đầu"
            ></b-form-datepicker>
            <label for="form-endDate">Ngày kết thúc</label>
            <b-form-datepicker
                id="form-endDate"
                v-model="form.endDate"
                locale="vi"
                :min="form.startDate ? form.startDate : new Date()"
                placeholder="Ngày kết thúc"
            ></b-form-datepicker>
        </b-modal>
    </div>
</template>

<script>
import AppNavBar from "@/components/AppNavBar";
import SubjectCard from "@/components/SubjectCard";

export default {
    name: "Subject",
    components: {SubjectCard, AppNavBar},
    data: function () {
        return {
            students: [],
            subjects: [],
            form: {
                name: '',
                numLessons: null,
                startDate: null,
                endDate: null
            }
        };
    },
    created() {
        this.$store.dispatch('subject/fetchSubjects').then(() => {
            this.subjects = this.$store.getters['subject/subjects']
        }).then(() => {
            this.$store.dispatch("student/fetchStudents").then(() => {
                this.students = this.$store.getters["student/students"];
            })
        })
    },
    methods: {
        createSubject: function () {
            this.$store.dispatch('subject/createSubject', {
                data: this.form
            }).then(() => {
                this.subjects = this.$store.getters['subject/subjects']
            })
        }
    }
}
</script>

<style scoped>
.page-content {
    padding: 5% 10% 0;
}

.button-right {
    display: flex;
    justify-content: flex-end;
}
</style>