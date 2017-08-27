package xyz.kots.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import xyz.kots.entity.Customer;

import java.net.URI;

public class RestClientUtil {

    public void getCustomerByIdDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/customer?id=1";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Customer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Customer.class);
        Customer customer = responseEntity.getBody();
        System.out.println("Id:"+customer.getId()+", Name:"+customer.getName()
                +", Phone:"+customer.getPhone()+", Address:"+customer.getAddress());
    }

    public void getCustomerByNameDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/customers/{name}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Customer[].class, "John");
        Customer[] customers = responseEntity.getBody();
        for(Customer customer : customers) {
            System.out.println("Id:"+customer.getId()+", Name:"+customer.getName()
                    +", Phone:"+customer.getPhone()+", Address:"+customer.getAddress());
        }
    }

    public void getAllCustomersDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/customers";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Customer[].class);
        Customer[] customers = responseEntity.getBody();
        for(Customer customer : customers) {
            System.out.println("Id:"+customer.getId()+", Name:"+customer.getName()
                    +", Phone:"+customer.getPhone()+", Address:"+customer.getAddress());
        }
    }

    public void addCustomerDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/customer";
        Customer objCustomer = new Customer();
        objCustomer.setName("John");
        objCustomer.setPhone("555-55-55");
        objCustomer.setAddress("Miami");
        HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(objCustomer, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }

    public void updateCustomerDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/customer";
        Customer objCustomer = new Customer();
        objCustomer.setId(1);
        objCustomer.setName("Zlatan");
        objCustomer.setPhone("111-11-11");
        objCustomer.setAddress("NY");
        HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(objCustomer, headers);
        restTemplate.put(url, requestEntity);
    }

    public static void main(String args[]) {
        RestClientUtil util = new RestClientUtil();
        util.getCustomerByIdDemo();
        util.getCustomerByNameDemo();
        util.getAllCustomersDemo();
        util.addCustomerDemo();
        util.updateCustomerDemo();
    }
}