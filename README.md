# Ứng dụng quản lý học sinh

> Báo cáo thực hành thực tập kỹ thuật FPT-Telecom

## Yêu cầu

- Cơ sở dữ liệu Mysql
- NodeJS
- JDK 11 hoặc cao hơn

## Cài đặt

### Clone

Clone repo theo đường dẫn https://github.com/danghh-1998/StudentManagement.git

### Mysql

```bash
mysql -uroot -p12345678
```

```mysql
CREATE DATABASE student_mângement CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
quit
```

### Backend

Chạy ứng dụng Java Spring trên IDE

![backend](https://raw.githubusercontent.com/danghh-1998/StudentManagement/master/screenshots/ide.gif)

## Frontend

- Di chuyển vào folder frontend và cài đặt các package cần thiết

```bash
cd frontend
npm install
```

- Chạy VueJS

```bash
npm run serve
```

> Mặc định backend chạy ở port 8080, frontend chạy ở port 8081

## Kết quả đạt được

- Thêm học sinh mới

![new student](https://raw.githubusercontent.com/danghh-1998/StudentManagement/master/screenshots/add_student.gif)

- Chỉnh sửa thông tin học sinh

![](https://raw.githubusercontent.com/danghh-1998/StudentManagement/master/screenshots/update_student.gif)

- Xóa học sinh

![](https://raw.githubusercontent.com/danghh-1998/StudentManagement/master/screenshots/delete_student.gif)

- Thêm môn học mới

![](https://raw.githubusercontent.com/danghh-1998/StudentManagement/master/screenshots/add_subject.gif)

- Chỉnh sửa thông tin môn học

![](https://raw.githubusercontent.com/danghh-1998/StudentManagement/master/screenshots/update_subject.gif)

- Xóa môn học

![](https://raw.githubusercontent.com/danghh-1998/StudentManagement/master/screenshots/delete_subject.gif)

- Xem danh sách môn học học sinh đã đăng ký, đăng ký, hủy đăng ký môn học cho học sinh

![](https://raw.githubusercontent.com/danghh-1998/StudentManagement/master/screenshots/register.gif)

- Xem danh sách sinh viên đăng ký một môn học, thống kê môn học có sinh viên đăng ký nhiều và số học sinh đăng ký môn hocjd đó

![](https://raw.githubusercontent.com/danghh-1998/StudentManagement/master/screenshots/view_students_registered.gif)

## Các công nghệ đã sử dụng

- Cơ sở dữ liệu: MySQL
- Backend: Java Spring Boot
- Frontend: VueJS
- Quản lý source code: Git

## Thành viên trong nhóm

- [ Phạm Ngọc Sơn ] : <son.pn172788@sis.hust.edu.vn>
- [ Trần Đại Dương ] : <duong.td173065@sis.hust.edu.vn>