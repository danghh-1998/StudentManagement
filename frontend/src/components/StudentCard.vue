<template>
    <div>
        <b-card
            :title="student.name"
            title-tag="h3"
            class="student-card"
            @dblclick="$bvModal.show(modalUpdateId)"
        >
            <b-card-text>
                Email: {{ student.email }}
            </b-card-text>
            <b-card-text>
                Giới tính: {{ student.gender === 'MALE' ? 'Nam' : 'Nữ' }}
            </b-card-text>
            <b-card-text>
                Lớp: {{ student.className }}
            </b-card-text>
            <b-card-text>
                Ngày sinh: {{ student.birthday | moment('[Ngày] DD [tháng] MM [năm] YYYY') }}
            </b-card-text>
            <b-link
                href="#"
                class="small"
                @click="$bvModal.show(modalRegisterId)"
            >Xem danh sách khóa học
            </b-link>
            <b-button-close
                class="button-close"
                @click="deleteStudent"
            />
        </b-card>
        <b-modal
            :id="modalUpdateId"
            @ok="updateStudent"
        >
            <template v-slot:modal-title>
                Chỉnh sửa thông tin
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
        <b-modal
            :id="modalRegisterId"
            hide-footer
            title="Danh sách môn học"
            size="lg"
        >
            <b-table :fields="fileds" :items="items">
                <template v-slot:cell(name)="data">
                    <b-link :to='`/subjects`'>
                        {{data.item.name}}
                    </b-link>
                </template>
                <template v-slot:cell(action)="data">
                    <b-button
                        variant="outline-danger"
                        v-if="data.item.action"
                        @click="cancelRegisterStudent(data.item.id)"
                    >Hủy đăng ký
                    </b-button>
                    <b-button
                        variant="outline-primary"
                        v-else
                        @click="registerStudent(data.item.id)"
                    >Đăng ký
                    </b-button>
                </template>
            </b-table>
        </b-modal>
    </div>
</template>

<script>
export default {
    name: "StudentCard",
    computed: {
        modalUpdateId: function () {
            return `modal-update-${this.student.id}`
        },
        modalRegisterId: function () {
            return `modal-register-${this.student.id}`
        },
    },
    props: {
        student: {
            type: Object,
            required: true
        }
    },
    data: function () {
        return {
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
                name: this.$props.student.name,
                email: this.$props.student.email,
                gender: this.$props.student.gender,
                className: this.$props.student.className,
                birthday: this.$props.student.birthday
            },
            fileds: [
                {
                    key: 'name',
                    label: 'Tên môn học',
                    sortable: false
                },
                {
                    key: 'action',
                    label: 'Hành động',
                    sortable: false
                }
            ],
            items: this.$store.getters['subject/subjects'].map(subject => {
                const registeredSubject = this.$store.getters['student/subjects'](this.student.id)
                return {
                    id: subject.id,
                    name: subject.name,
                    action: registeredSubject ? !!registeredSubject.find(item => item.id === subject.id) : false
                }
            })
        };
    },
    methods: {
        updateStudent: function () {
            this.$store.dispatch('student/updateStudent', {
                id: this.student.id,
                data: this.form
            })
        },
        deleteStudent: function () {
            this.$store.dispatch('student/deleteStudent', {
                id: this.student.id
            })
        },
        registerStudent: function (subjectId) {
            this.$store.dispatch('student/registerStudent', {
                id: this.student.id,
                subjectId: subjectId
            }).then(() => {
                this.items = this.$store.getters['subject/subjects'].map(subject => {
                    return {
                        id: subject.id,
                        name: subject.name,
                        action: !!this.$store.getters['student/subjects'](this.student.id).find(item => item.id === subject.id)
                    }
                })
            })
        },
        cancelRegisterStudent: function (subjectId) {
            this.$store.dispatch('student/cancelregisterStudent', {
                id: this.student.id,
                subjectId: subjectId
            }).then(() => {
                this.items = this.$store.getters['subject/subjects'].map(subject => {
                    return {
                        id: subject.id,
                        name: subject.name,
                        action: !!this.$store.getters['student/subjects'](this.student.id).find(item => item.id === subject.id)
                    }
                })
            })
        }
    }
}
</script>

<style scoped>
.card {
    border-radius: 1rem;
    margin-top: 1.5rem;
    box-shadow: 5px 5px 15px 5px rgba(136, 136, 136, 0.25);
}

.student-card {
    position: relative;
}

.button-close {
    position: absolute;
    right: 1.25rem;
    top: 1.25rem;
    visibility: hidden;
}

.card:hover .button-close {
    visibility: visible;
}

.button-close:hover {
    color: #cb3a63;
}
</style>