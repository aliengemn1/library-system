package com.example.library.patron.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patrons")
public class Patron {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Email(message = "Email must be valid")
    @Column(unique = true, nullable = false)
    private String email;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be valid")
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "zip_code")
    private String zipCode;
    
    @Column(name = "country")
    private String country = "USA";
    
    @Enumerated(EnumType.STRING)
    @Column(name = "patron_type")
    private PatronType patronType = PatronType.STUDENT;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PatronStatus status = PatronStatus.ACTIVE;
    
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    
    @Column(name = "max_books_allowed")
    private Integer maxBooksAllowed = 5;
    
    @Column(name = "current_books_checked_out")
    private Integer currentBooksCheckedOut = 0;
    
    @Column(name = "total_fines")
    private Double totalFines = 0.0;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    // Constructors
    public Patron() {
        this.registrationDate = LocalDateTime.now();
        this.expirationDate = LocalDate.now().plusYears(1);
    }
    
    public Patron(String firstName, String lastName, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public PatronType getPatronType() {
        return patronType;
    }
    
    public void setPatronType(PatronType patronType) {
        this.patronType = patronType;
    }
    
    public PatronStatus getStatus() {
        return status;
    }
    
    public void setStatus(PatronStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public Integer getMaxBooksAllowed() {
        return maxBooksAllowed;
    }
    
    public void setMaxBooksAllowed(Integer maxBooksAllowed) {
        this.maxBooksAllowed = maxBooksAllowed;
    }
    
    public Integer getCurrentBooksCheckedOut() {
        return currentBooksCheckedOut;
    }
    
    public void setCurrentBooksCheckedOut(Integer currentBooksCheckedOut) {
        this.currentBooksCheckedOut = currentBooksCheckedOut;
    }
    
    public Double getTotalFines() {
        return totalFines;
    }
    
    public void setTotalFines(Double totalFines) {
        this.totalFines = totalFines;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    // Helper methods
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public boolean isActive() {
        return status == PatronStatus.ACTIVE && 
               (expirationDate == null || expirationDate.isAfter(LocalDate.now()));
    }
    
    public boolean canCheckOutBooks() {
        return isActive() && currentBooksCheckedOut < maxBooksAllowed;
    }
    
    public enum PatronType {
        STUDENT, FACULTY, STAFF, COMMUNITY, ALUMNI
    }
    
    public enum PatronStatus {
        ACTIVE, INACTIVE, SUSPENDED, EXPIRED
    }
} 