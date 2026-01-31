# Order & Invoice Management System

## 📌 Project Overview
The **Order & Invoice Management System** is a Spring Boot–based backend application that manages products, generates invoices for single and multiple product purchases, validates stock availability, and processes payments strictly based on generated invoices.

This project follows clean architecture principles with proper separation of concerns using Controller, Service, DTO, Mapper, and Exception layers.

---

## 🚀 Features

### 👨‍💼 Admin Features
- Admin sign-up and login
- Add single product
- Add multiple products in bulk
- Manage product inventory with quantity control

### 🛒 User Features
- View all available products
- Buy a single product
- Buy multiple products at once
- Automatic invoice generation
- Stock validation (cannot buy more than available quantity)

### 🧾 Invoice & Payment
- Invoice generated before payment
- Unique invoice ID for each purchase
- Discount applied based on total amount
- Payment allowed only using valid invoice ID
- Secure payment amount fetched from invoice
- Supports CARD, UPI, and CASH payment modes

---

## 🧠 Business Rules Implemented
- Users cannot purchase more items than available stock
- Invoice must be generated before payment
- Payment amount cannot be manipulated by user
- Optional fields (like description) appear only if provided
- Supports both single-product and multi-product purchases

---

## 🛠️ Tech Stack
- Java
- Spring Boot
- Maven
- MapStruct (DTO ↔ Entity mapping)
- REST APIs
- In-memory data storage (HashMap)

---

## 📂 Project Structure
