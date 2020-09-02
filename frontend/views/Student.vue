<template>
    <div class="page">
        <app-nav-bar/>
        <div class="page-content">
            <div class="button-right">
                <b-button
                    class="button-right"
                    variant="primary"
                    @click="$bvModal.show('modal-create')"
                >Thêm học sinh mới
                </b-button>
            </div>
            <b-card-group columns>
                <StudentCard v-for="student in students" :key="student.id" :student="student"/>
            </b-card-group>
        </div>
        <b-modal
            id="modal-create"
            @ok="createStudent"
        >
            <template v-slot:modal-title>
                Thêm học sinh mới
            </template>
            <b-form-group
                id="name"
                label="Họ và tên"
                label-for="form-name"
            >
                <b-form-input
                    id="form-name"
                    v-model="form.name"
                    required
                    placeholder="Nhập tên học sinh"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                id="email"
                label="Email"
                label-for="form-email"
            >
                <b-form-input
                    id="form-email"
                    v-model="form.email"
                    required
                    type="email"
                    placeholder="Email"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                id="gender"
                label="Giới tính"
                label-for="form-gender"
            >
                <b-form-select v-model="form.gender"
                               :options="genderType"
                >
                    <template v-slot:first>
                        <b-form-select-option
                            :value="null"
                            disabled
                        >
                            Giới tính
                        </b-form-select-option>
                    </template>
                </b-form-select>
            </b-form-group>
            <b-form-group
                id="class-name"
                label="Lớp"
                label-for="form-class-name"
            >
                <b-form-input
                    id="form-class-name"
                    v-model="form.className"
                    required
                    placeholder="Lớp"
                ></b-form-input>
            </b-form-group>
            <label for="form-birthday">Ngày sinh</label>
            <b-form-datepicker
                id="form-birthday"
                v-model="form.birthday"
                locale="vi"
                :max="new Date()"
                placeholder="Ngày sinh"
            ></b-form-datepicker>
        </b-modal>
    </div>
</template>

<script>
import AppNavBar from "@/components/AppNavBar";
import StudentCard from "@/components/StudentCard";

export default {
    name: "Student",
    components: {StudentCard, AppNavBar},
    data: function () {
        return {
            students: [],
            subjects: [],
            genderType: [
                {
                    value: 'MALE',
                    text: 'Nam'
                },
                {
                    value: 'FEMALE',
                    text: 'Nữ'
                },
            ],
            form: {
                name: '',
                email: '',
                gender: null,
                className: '',
                birthday: null
            }
        };
    },
    created() {
        this.$store.dispatch("student/fetchStudents").then(() => {
            this.students = this.$store.getters["student/students"];
        })
        .then(() => {
            this.$store.dispatch('subject/fetchSubjects').then(() => {
                this.subjects = this.$store.getters['subject/subjects']
            })
        })
    },
    methods: {
        createStudent: function () {
            this.$store.dispatch('student/createStudent', {
                data: this.form
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