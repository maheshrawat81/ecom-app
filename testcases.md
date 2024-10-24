# Test Cases Documentation

This document outlines the test cases for the `ProductController` class in the `product_catalog` module. Each test case verifies the functionality of various endpoints related to product management, ensuring that the controller interacts correctly with the `ProductService`.

## Test Cases

### 1. Should Return All Products

- **Test Method**: `should_return_all_products()`
- **HTTP Method**: `GET`
- **Endpoint**: `/api/products`
- **Expected Response**:
    - **Status**: `200 OK`
    - **Response Body**:
    ```json
    [
        {
            "name": "Test Product",
            "seller_name": "seller1",
            "price": 10.0,
            "quantity": 4,
            "description": "Test description"
        }
    ]
    ```
- **Description**: This test verifies that the endpoint returns a list of all products in the system.

---

### 2. Should Create a New Product

- **Test Method**: `should_create_a_new_product()`
- **HTTP Method**: `POST`
- **Endpoint**: `/api/products`
- **Request Body**:
    ```json
    {
        "name": "New Product",
        "seller_name": "seller1",
        "price": 12.5,
        "quantity": 2,
        "description": "updated description"
    }
    ```
- **Expected Response**:
    - **Status**: `200 OK`
- **Description**: This test verifies that a new product can be successfully created using the POST endpoint.

---

### 3. Should Get Product by ID

- **Test Method**: `should get product by id()`
- **HTTP Method**: `GET`
- **Endpoint**: `/api/products/1`
- **Expected Response**:
    - **Status**: `200 OK`
    - **Response Body**:
    ```json
    {
        "name": "Test Product",
        "seller_name": "seller1",
        "price": 10.0,
        "quantity": 5,
        "description": "A sample product"
    }
    ```
- **Description**: This test checks that a specific product can be retrieved by its ID.

---

### 4. Should Update an Existing Product

- **Test Method**: `should update an existing product()`
- **HTTP Method**: `PUT`
- **Endpoint**: `/api/products/1`
- **Request Body**:
    ```json
    {
        "name": "Test Product",
        "seller_name": "username",
        "price": 15.0,
        "quantity": 5,
        "description": "updated description"
    }
    ```
- **Expected Response**:
    - **Status**: `200 OK`
    - **Response Body**:
    ```json
    {
        "name": "Test Product",
        "seller_name": "username",
        "price": 15.0,
        "quantity": 5,
        "description": "updated description"
    }
    ```
- **Description**: This test verifies that an existing product can be updated successfully.

---

### 5. Should Delete a Product

- **Test Method**: `should delete a product()`
- **HTTP Method**: `DELETE`
- **Endpoint**: `/api/products/1`
- **Expected Response**:
    - **Status**: `204 No Content`
- **Description**: This test ensures that a product can be deleted successfully, returning a 204 status with no content.

---

## Test Results

[Click here to see the test results](path/to/your/file.html)

---

## Test Execution

All test cases were executed successfully using the command:

```bash
./gradlew test
