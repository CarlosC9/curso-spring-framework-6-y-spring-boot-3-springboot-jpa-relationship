package com.carlos.curso.springboot.jpa.springbootjparelationship;

import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.Address;
import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.ClientDetails;
import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.Course;
import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.Student;
import com.carlos.curso.springboot.jpa.springbootjparelationship.repositories.ClientDetailsRepository;
import com.carlos.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.carlos.curso.springboot.jpa.springbootjparelationship.repositories.CourseRepository;
import com.carlos.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;

import com.carlos.curso.springboot.jpa.springbootjparelationship.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

  private ClientRepository clientRepository;

  private InvoiceRepository invoiceRepository;

  private ClientDetailsRepository clientDetailsRepository;

  private StudentRepository studentRepository;

  private CourseRepository courseRepository;

  public static void main(String[] args) {
    SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
  }

  @Transactional
  public void manyToOne() {
    Client client = new Client("John", "Doe");
    this.clientRepository.save(client);
    Invoice invoice = new Invoice("compras de oficina", 2000L);
    invoice.setClient(client);
    Invoice invoiceDb = this.invoiceRepository.save(invoice);
    System.out.println(invoiceDb);

  }

  @Transactional
  public void manyToOneFindById() {
    Optional<Client> optionalClient = this.clientRepository.findById(1L);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.orElseThrow();
      Invoice invoice = new Invoice("manyToOneFindById", 2000L);
      invoice.setClient(client);
      Invoice invoiceDb = this.invoiceRepository.save(invoice);
      System.out.println(invoiceDb);
    }
  }

  @Transactional
  public void oneToMany() {
    Client client = new Client("Fran", "Moras");

    Address address1 = new Address("El verjel", 1234);
    Address address2 = new Address("Vasxo de Gama", 9875);

    address1.setClient(client);
    address2.setClient(client);

    this.clientRepository.save(client);
  }

  @Transactional
  public void oneToManyFindById() {
    Optional<Client> optionalClient = this.clientRepository.findById(4L);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.orElseThrow();

      Address address1 = new Address("El verjel", 1234);
      Address address2 = new Address("Vasxo de Gama", 9875);

      address1.setClient(client);
      address2.setClient(client);

      client.getAddresses().add(address1);
      client.getAddresses().add(address2);

      this.clientRepository.save(client);
    }
  }

  @Transactional
  public void removeAddress() {
    Optional<Client> optionalClient = this.clientRepository.findById(4L);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.orElseThrow();

      if (!client.getAddresses().isEmpty()) {
        client.getAddresses().remove(0);
        this.clientRepository.save(client);
      }

    }

  }

  @Transactional
  public void oneToManyInvoiceBidireccional() {
    Client client = new Client("Carlos", "Caraballo");

    Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
    Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

    client
      .addInvoice(invoice1)
      .addInvoice(invoice2);

    this.clientRepository.save(client);

  }

  @Transactional
  public void oneToManyInvoiceBidireccionalFindById() {

    Optional<Client> optionalClient = this.clientRepository.findById(2L);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.orElseThrow();

      Invoice invoice1 = new Invoice("Compras de ocio", 5000L);
      Invoice invoice2 = new Invoice("Compras de autumoviles", 8000L);

      client
        .addInvoice(invoice1)
        .addInvoice(invoice2);

      this.clientRepository.save(client);
    }

  }

  @Transactional
  public void removeInvoiceBidireccional() {
    Optional<Client> optionalClient = this.clientRepository.findById(2L);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.orElseThrow();

      if (!client.getInvoices().isEmpty()) {
        client.getInvoices().remove(0);
        this.clientRepository.save(client);
      }
    }
  }

  @Transactional
  public void oneToOne() {
    Optional<Client> optionalClient = this.clientRepository.findById(2L);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.orElseThrow();
      ClientDetails clientDetails = new ClientDetails(true, 5000);
      clientDetails.setClient(client);
      clientDetailsRepository.save(clientDetails);
    }

  }

  @Transactional
  public void oneToOneBidireccional() {
    Optional<Client> optionalClient = this.clientRepository.findById(2L);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.orElseThrow();
      ClientDetails clientDetails = new ClientDetails(true, 5000);
      client.setClientDetails(clientDetails);
      clientDetails.setClient(client);
      this.clientRepository.save(client);
    }
  }

  public void removeOneToOneClientDetails() {
    Optional<Client> optionalClient = this.clientRepository.findById(2L);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.orElseThrow();
      client.setClientDetails(null);
      this.clientRepository.save(client);
    }
  }

  @Transactional
  public void manyToMany() {
    Student student1 = new Student("Jano", "Pura");
    Student student2 = new Student("Erba", "Doe");

    Course course1 = new Course("Curso de java master", "Andres");
    Course course2 = new Course("Curso de Spring boot", "Andres");

    student1.addCourse(course1).addCourse(course2);
    student2.addCourse(course2);

    this.studentRepository.saveAll(Arrays.asList(student1, student2));

  }

  @Transactional
  public void manyToManyFindById() {
    Optional<Student> optionalStudent = this.studentRepository.findById(8L);
    Optional<Course> optionalCourse = this.courseRepository.findById(1L);

    if (optionalCourse.isPresent() && optionalStudent.isPresent()) {
      Student student = optionalStudent.orElseThrow();
      Course course = optionalCourse.orElseThrow();

      student.addCourse(course);

      this.studentRepository.save(student);
    }

  }

  @Transactional
  public void manyToManyRemove() {
    Optional<Student> optionalStudent = this.studentRepository.findById(8L);

    if (optionalStudent.isPresent()) {
      Student student = optionalStudent.orElseThrow();

      Optional<Course> optionalCourse = student.getCourses().stream()
        .filter(item -> item.getId().equals(2L))
        .findFirst();

      if (optionalCourse.isPresent()) {
        student.getCourses().remove(optionalCourse.orElseThrow());
      }

      this.studentRepository.save(student);
    }

  }

  @Override
  public void run(String... args) throws Exception {
//        this.manyToOne();
//    this.manyToOneFindById();
//    this.oneToMany();
//    this.oneToManyFindById();
//    this.removeAddress();
//    this.oneToManyInvoiceBidireccional();
//    this.removeInvoiceBidireccional();
//    this.oneToOne();
//    this.oneToOneBidireccional();
//    this.removeOneToOneClientDetails();
//    this.manyToMany();
//    this.manyToManyFindById();
    this.manyToManyRemove();
  }

  @Autowired
  public void setClientRepository(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Autowired
  public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
    this.invoiceRepository = invoiceRepository;
  }

  @Autowired
  public void setClientDetailsRepository(ClientDetailsRepository clientDetailsRepository) {
    this.clientDetailsRepository = clientDetailsRepository;
  }

  @Autowired
  public void setStudentRepository(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Autowired
  public void setCourseRepository(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }
}
