# Pretest_gallery

ข้อที่ 6
System Architecture Overview
โปรเจกต์นี้ออกแบบภายใต้โครงสร้าง 3-Tier Architecture เพื่อแยกส่วนการทำงานให้ชัดเจนและรองรับการขยายตัวในอนาคต:

Frontend (Presentation Layer)

Technology: React (Single-Page Application)

Responsibility: จัดการ UI/UX, การทำ Infinite Scroll, และการกรองรูปภาพ (Filtering) ตาม Hashtags

Hosting: Deploy บน Render Static Site พร้อมระบบ CDN เพื่อความรวดเร็วในการเข้าถึงข้อมูล

Backend (Logic Layer)

Technology: Java 21 / Spring Boot 3.x

Responsibility: ให้บริการ RESTful API เพื่อเชื่อมต่อกับฐานข้อมูล, จัดการ Business Logic เช่นการค้นหาแบบ Partial Match และการแบ่งหน้าข้อมูล (Pagination)

Runtime: รันในรูปแบบ Docker Container เพื่อให้มั่นใจเรื่อง Consistency ของสภาพแวดล้อมบน Production

Database (Data Layer)

Technology: PostgreSQL 16

Data Model: จัดเก็บข้อมูลแบบ Relational โดยใช้ความสัมพันธ์แบบ Many-to-Many ระหว่างตาราง images และ tags เพื่อรองรับ Hashtags ไม่จำกัดต่อหนึ่งรูปภาพ

Production Specifications
Hosting Platform: Render (PaaS)

Region: Oregon (US West)

Infrastructure:

Web Service (API): Managed Container Service

Static Site (Frontend): Automated Build & Deploy Pipeline

Database: Managed PostgreSQL (High Availability)

Deployment Workflow (CI/CD)
เราใช้ระบบ Automated Deployment ผ่าน GitHub เพื่อลดความผิดพลาดและเพิ่มความรวดเร็วในการอัปเดตระบบ:

Version Control: เก็บซอร์สโค้ดแยก Repository (Frontend / Backend) บน GitHub

Continuous Deployment: เมื่อมีการ git push เข้าสู่ branch main:

Frontend: รันคำสั่ง npm install && npm run build เพื่อ Generate ไฟล์ Production

Backend: ทำการ Build Docker Image ใหม่จาก Dockerfile อัตโนมัติ

Zero Downtime Deployment: ระบบจะทำการสลับ Traffic ไปยังเวอร์ชันใหม่เมื่อการ Build และ Health Check สำเร็จเท่านั้น

Infrastructure Diagram
Plaintext
[ User Browser ]
       |
       v (HTTPS)
+-----------------------+      +-----------------------+
|  Frontend (React)     | <--> | Backend (Spring Boot) |
|  Render Static Site   |      | Render Docker Service |
+-----------------------+      +-----------+-----------+
                                           |
                                           v
                               +-----------------------+
                               | Database (PostgreSQL) |
                               | Render Managed DB     |
                               +-----------------------+
