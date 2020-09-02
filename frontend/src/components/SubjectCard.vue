<template>
    <div>
        <b-card
            :title="subject.name"
            title-tag="h3"
            class="student-card"
            @dblclick="$bvModal.show(modalUpdateId)"
        >
            <b-card-text>
                Số tiết học: {{ subject.numLessons }}
            </b-card-text>
            <b-card-text>
                Ngày bắt đầu: {{ subject.startDate | moment('[Ngày] DD [tháng] MM [năm] YYYY') }}
            </b-card-text>
            <b-card-text>
                Ngày kết thúc: {{ subject.endDate | moment('[Ngày] DD [tháng] MM [năm] YYYY') }}
            </b-card-text>
            <b-link
                v-if="subject.students.length > 0"
                class="small"
                href="#"
                @click="$bvModal.show(modalStudentId)"
            >
                {{`Có ${subject.students.length} học sinh đăng ký môn học này`}}
            </b-link>
            <b-card-text
                v-else
                class="small primary-text"
            >
                Không có học sinh nào đăng ký môn học này
            </b-card-text>
            <b-button-close
                class="button-close"
                @click="deleteSubject"
            />
        </b-card>
        <b-modal
            :id="modalUpdateId"
            @ok="updateSubject"
        >
            <template v-slot:modal-title>
                Chỉnh sửa thông tin
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
        <b-modal
            :id="modalStudentId"
            hide-footer
            title="Danh sách sinh viên đăng ký"
            size="lg"
        >
            <b-list-group>
                <b-list-group-item
                    v-for="student in subject.students"
                    :key="student.id"
                >
                    {{student.name}}
                </b-list-group-item>
            </b-list-group>
        </b-modal>
    </div>
</template>

<script>
export default {
    name: "SubjectCard",
    props: {
        subject: {
            type: Object,
            required: true
        }
    },
    data: function () {
        return {
            form: {
                name: this.$props.subject.name,
                numLessons: this.$props.subject.numLessons,
                startDate: this.$props.subject.startDate,
                endDate: this.$props.subject.endDate
            }
        }
    },
    computed: {
        modalUpdateId: function () {
            return `modal-update-${this.subject.id}`
        },
        modalStudentId: function () {
            return `modal-student-${this.subject.id}`
        }
    },
    methods: {
        updateSubject: function () {
            this.$store.dispatch('subject/updateSubject', {
                id: this.subject.id,
                data: this.form
            })
        },
        deleteSubject: function () {
            this.$store.dispatch('subject/deleteSubject', {
                id: this.subject.id
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

.primary-text {
    color: #2C52FF;
}
</style>