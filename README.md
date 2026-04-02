# Pretest Gallery

ระบบแกลเลอรีสำหรับแสดงรูปภาพพร้อม **Hashtags Filtering** และ **Infinite Scroll**  
ออกแบบด้วยสถาปัตยกรรมแบบ **3-Tier Architecture** เพื่อแยกหน้าที่ของระบบอย่างชัดเจน และรองรับการขยายในอนาคต

---

# System Architecture Overview

โปรเจกต์นี้ใช้โครงสร้าง **3-Tier Architecture**

---

# Frontend 

**Technology**
- React (Single Page Application)

**Responsibilities**
- จัดการ **UI / UX**
- รองรับ **Infinite Scroll**
- ระบบ **Hashtag Filtering**

**Hosting**
- Deploy บน **Render Static Site**
- ใช้ **CDN** เพื่อเพิ่มความเร็วในการโหลดข้อมูล

---

# Backend 

**Technology**

- Java 21
- Spring Boot 3.x

**Responsibilities**

- ให้บริการ **RESTful API**
- จัดการ **Business Logic**
- ค้นหาข้อมูลแบบ **Partial Match**
- รองรับ **Pagination**

**Runtime Environment**

- รันผ่าน **Docker Container**
- เพื่อให้ Environment ของระบบ **เหมือนกันทุกที่ (Consistency)**

---

# Database 

**Technology**

- PostgreSQL 16

**Data Model**

- ใช้ความสัมพันธ์แบบ Many-to-Many ระหว่างตาราง images และ tags ผ่านตารางกลาง image_tags เพื่อรองรับการใส่ Hashtag ได้ไม่จำกัดต่อหนึ่งรูปภาพ

- มีการใช้ Index ในคอลัมน์สำคัญเพื่อเพิ่มความเร็วในการค้นหาข้อมูล (Query Optimization)

---

# Production Specifications & Deployment

**Hosting Platform: Render (PaaS) | Region: Oregon (US West)**

- Infrastructure: ใช้บริการ Managed Services ทั้งหมด (Database, Web Service, Static Site) เพื่อความปลอดภัยและการสำรองข้อมูลอัตโนมัติ

*CI/CD Workflow:*

- Version Control: แยก Repository ของ Frontend และ Backend บน GitHub

- Continuous Deployment: เมื่อมีการ git push เข้าสู่ branch main ระบบจะทำการ Auto-build และ Auto-deploy ทันที

- Zero Downtime: ระบบจะตรวจสอบสถานะ (Health Check) ของเวอร์ชันใหม่ให้เรียบร้อยก่อนสลับ Traffic เพื่อไม่ให้ผู้ใช้งานได้รับผลกระทบระหว่างการอัปเดต
  
### Diagram

```text
[ User Browser ]
       |
       v (HTTPS)
+-----------------------+      +-----------------------+
|   Frontend (React)    | <--> | Backend (Spring Boot) |
|  Render Static Site   |      | Render Docker Service |
+-----------------------+      +-----------+-----------+
                                           |
                                           v
                               +-----------------------+
                               | Database (PostgreSQL) |
                               |  Render Managed DB    |
                               +-----------------------+

