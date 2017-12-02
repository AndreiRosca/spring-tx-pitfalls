package com.endava.spring.tx.pitfalls.domain;

/**
 * Created by anrosca on Dec, 2017
 */
public class Employee implements Comparable<Employee> {

    private String id;
    private String firstName;
    private String lastName;
    private String domainName;
    private String email;
    private String joiningDate;

    private Employee() {
    }

    @Override
    public int compareTo(Employee other) {
        return domainName.compareTo(other.domainName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        return domainName != null ? domainName.equals(employee.domainName) : employee.domainName == null;
    }

    @Override
    public int hashCode() {
        return domainName != null ? domainName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", domainName='" + domainName + '\'' +
                ", email='" + email + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public static EmployeeBuilder newBuilder() {
        return new EmployeeBuilder();
    }

    public static final class EmployeeBuilder {
        private Employee employee = new Employee();

        private EmployeeBuilder() {
        }

        public EmployeeBuilder setId(String id) {
            employee.id = id;
            return this;
        }

        public EmployeeBuilder setFirstName(String firstName) {
            employee.firstName = firstName;
            return this;
        }

        public EmployeeBuilder setLastName(String lastName) {
            employee.lastName = lastName;
            return this;
        }

        public EmployeeBuilder setDomainName(String domainName) {
            employee.domainName = domainName;
            return this;
        }

        public EmployeeBuilder setEmail(String email) {
            employee.email = email;
            return this;
        }

        public EmployeeBuilder setJoiningDate(String joiningDate) {
            employee.joiningDate = joiningDate;
            return this;
        }

        public Employee build() {
            return employee;
        }
    }
}
