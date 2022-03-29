package test;


import alzq.Asset;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class CollectorsTest {

    public static void main(String[] args) {
        List<Asset> assets = new ArrayList<>();
        Asset asset1 = new Asset("餅乾1", "1341", 13L, 110D);
        Asset asset2 = new Asset("餅乾2", "DAS", 14L, 1D);
        Asset asset3 = new Asset("加油1", "加油1", 5L, 13D);
        Asset asset4 = new Asset("加油2", "1342", 1L, 1D);
        assets.add(asset1);
        assets.add(asset2);
        assets.add(asset3);
        assets.add(asset4);

        Optional<Asset> max = assets.stream().max(Comparator.comparing(Asset::getNumber));
        Asset asset = max.get();
        Comparator<Asset> byName = Comparator.comparing(Asset::getNumber);
        Comparator<Asset> bySizeDesc = Comparator.comparing(Asset::getSize).reversed();
        assets.sort(byName.thenComparing(bySizeDesc));
        System.out.println(assets);


        System.out.println(max);
//        System.out.println(assets);
        Map<String, Long> collect = assets.stream().collect(Collectors.groupingBy(Asset::getAssetName, Collectors.summingLong(x -> {
            return x.getSize().longValue();
        })));
        System.out.println("collect:" + collect);

        Map<String, Long> applyAssetCount = new HashMap<>(4);
        applyAssetCount.put("衣服", 2L);
        applyAssetCount.put("裤子", 3L);
        Map<String, Long> personAssetsCount = new HashMap<>(4);
        personAssetsCount.put("商业", 2L);
        personAssetsCount.put("裤子", 3L);
        Map<String, Long> merged = Stream.concat(applyAssetCount.entrySet().stream(), personAssetsCount.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));

        System.out.println(merged);

        List<Asset> assetList = assets.stream().filter(x -> !x.getAssetName().equals(x.getAssetCode())).collect(Collectors.toList());

        assets.removeIf(x -> x.getAssetName().equals(x.getAssetCode()));
        System.out.println(assetList);
        System.out.println(assets);

        Optional<Asset> optionalAsset = assets.stream().filter(x -> "1341".equals(x.getAssetCode())).findFirst();

        List<Long> collect1 = assetList.stream().map(Asset::getNumber).collect(Collectors.toList());

        Map<String, Asset> collect2 = assets.stream().collect(Collectors.toMap(Asset::getAssetName, Function.identity(), (oldData, newData) -> newData));

        Integer[] myArray = {1, 2, 3};
        List myList = Arrays.stream(myArray).collect(Collectors.toList());
        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int[] myArray2 = {1, 2, 3};
        List myList1 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());

        List<Integer> integers = Arrays.asList(myArray);

//        System.out.println(assetList);
//        System.out.println(optionalAsset);
    }

}
