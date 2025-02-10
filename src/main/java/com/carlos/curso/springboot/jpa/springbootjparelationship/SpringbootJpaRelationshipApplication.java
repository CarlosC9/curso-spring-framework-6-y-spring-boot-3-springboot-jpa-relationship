package com.carlos.curso.springboot.jpa.springbootjparelationship;

import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.carlos.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.carlos.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

  private ClientRepository clientRepository;

  private InvoiceRepository invoiceRepository;

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

  @Override
  public void run(String... args) throws Exception {
//        this.manyToOne();
//    this.manyToOneFindById();
  }

  @Autowired
  public void setClientRepository(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Autowired
  public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
    this.invoiceRepository = invoiceRepository;
  }
}
