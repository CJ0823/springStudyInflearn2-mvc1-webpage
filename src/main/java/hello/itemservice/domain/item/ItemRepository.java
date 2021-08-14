package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

  private static final Map<Long, Item> store = new HashMap<>();
  private static Long sequence = 0L;

  public Item save(Item item) {
    item.setId(++sequence);
    store.put(item.getId(), item);
    return item;
  }

  public Item findById(Long id) {
    return store.get(id);
  }

  //store 값 보호를 위해 List로 한번 감쌈
  public List<Item> findAll() {
    return new ArrayList<>(store.values());
  }

  public void update(Long itemId, Item updateParam) {
    Item findItem = findById(itemId);

    //원래는 updateParam을 위한 별도 DTO가 필요. setId를 해버릴 수도 있음
    findItem.setItemName(updateParam.getItemName());
    findItem.setPrice(updateParam.getPrice());
    findItem.setQuantity(updateParam.getQuantity());
  }

  public void clearStore() {
    store.clear();
  }

}
