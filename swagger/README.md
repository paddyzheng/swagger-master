# Project Overview

# User Requirement

# Technical Architecture: Based on lightweight considerations
## springboot
## swagger：Display layer，REST API
## springmvc
## spring JpaRepository
## H2 DB

# development environment 
## idea run SwaggerApplication.java   if fail to start up then check application.yml，url: jdbc:h2:C:/H2/db02  whether it exist or not
## Application Address：http://127.0.0.1:8080/doc.html#/default/Online-BookStore/add，  Pay attention to switching the English version in the upper right corner


## DB：http://127.0.0.1:8080/h2/login.jsp?jsessionid=760cbe68ca061da2bb3eef970096dba3
url: jdbc:h2:C:/H2/db02 
username: root 
password: syc123 
if fail to start up then check application.yml

# Module Details
## Application Function Module
### bookManagement-add：Add a new book, click the debug button on the left, enter the parameters and send a request to view the returned results, or directly query the data on the H2 DB
### bookManagement-list：Unconditionally search for all books, click the debug button on the left to send a request without entering parameters, and view the returned results
### bookManagement-update：Update the book, click the debug button on the left, enter the parameters, and send the request. The data comes from the return result of bookManagement-list above
### bookManagement-queryBooksByAuthorLike：Fuzzy query of book list based on author
### bookManagement-queryBooksByPriceGreaterThan：Fuzzy query of book list based on price (greater than the input price)
### bookManagement-queryBooksByPriceLessThan：Fuzzy query of book list based on price (less than the input price)
### bookManagement-queryBooksByTitleLike：Fuzzy query of book list based on title
### bookManagement-delete：Delete data, bookId comes from the return result of bookManagement-list above
### shopping cart add：Add to cart, bookId comes from the return result of bookManagement-list
### shopping cart list：Unconditional search shopping cart
### shopping cart checkOut：Shopping cart checkout, cartId comes from the return result of the shopping cart list above

## Code logic module
### SwaggerApplication: springboot startup 
### BookStoreController：MVC Design pattern Controller
### Service: BookService and CartService are data processing for tables tm-book and tm_cart, including add/delete/update/query methods
### Model: TmBook and TmCart are DTOs
### repository: BookRepository and CartRepository are the management of data by Spring JpaRepository
### config: SwaggerConfig

# Testing and Debugging
## CartServiceTest and BookServiceTest: are data processing for tables tm-book and tm_cart (including add/delete/update/query methods)

# Deployment and Release

# appendix

