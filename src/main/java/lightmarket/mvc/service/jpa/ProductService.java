package lightmarket.mvc.service.jpa;

import lightmarket.mvc.model.domain.Producer;
import lightmarket.mvc.model.domain.Product;
import lightmarket.mvc.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * CRUD
     */

    public Product createProduct(Product product){
        Product result = productRepository.save(product);
        return result;
    }

    public Product getProductById(long productId){
        if (productRepository.existsById(productId)){
            return productRepository.getOne(productId);
        } else{
            return null;
        }
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProductById(long productId){
//        if (productRepository.existsById(productId)){
//            Product product = productRepository.getOne(productId);
//        System.out.println("Product: ");
//        System.out.println(product);
//            productRepository.delete(product);
        System.out.println("productId " + productId);
            productRepository.deleteProductById(productId);
            productRepository.flush();

//            return true;
//        } else {
//            return false;
//        }
    }

    /**
     * Additional
     */

    /**
     * @param producer
     * @return List of products of own producer
     */

    public List<Product> getProductListByProducer(Producer producer) {
        return productRepository.findAllByProducerId(producer.getId());
    }

    public List<Product> getProductListPageByProducerId(long producerId, int pageNumber, int count){
        int offset = count * pageNumber;
        if (productRepository.existsById(producerId)) {
            return productRepository.findPageProductList(producerId, count, offset);
        }
        return null;
    }

    public List<Product> getAllOffset(int pageNumber, int countVisiblePage) {
        int offset = pageNumber * countVisiblePage;
        return productRepository.findAllByOffset(countVisiblePage, offset);
    }

    public long getCountOfProducts(long producerId) {
        return productRepository.getCountOfProductsByProducerId(producerId);
    }

}
