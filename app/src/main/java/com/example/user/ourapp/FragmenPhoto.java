package com.example.user.ourapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class FragmenPhoto extends android.app.Fragment {

    RecyclerView recyclerView;

    List<Product> productList;

    ProductAdapter adapter;

    public FragmenPhoto() {
        // Required empty public constructor
    }

    public static FragmenPhoto newInstance(String param1, String param2) {
        FragmenPhoto fragment = new FragmenPhoto();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragmen_photo, container, false);

        productList = new ArrayList<>();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productList.add(
                new Product(
                        1,
                        "RecyclerView",
                        "Прокручивается список, создаются вьюхи и выводятся на экран, при этом выполняется onCreateViewHolder() и onBindViewHolder().",
                        5,
                        1000000,
                        R.drawable.macbook));

        productList.add(
                new Product(
                        1,
                        "Fragment",
                        "Сами фрагменты наследуются от android.app.Fragment. Существует подклассы фрагментов: ListFragment, DialogFragment, PreferenceFragment, WebViewFragment и др.",
                        5,
                        1000000,
                        R.drawable.dellinspiron));

        productList.add(
                new Product(
                        1,
                        "Adapter",
                        "В Android часто используются адаптеры. Если говорить в общих чертах, то адаптеры упрощают связывание данных с элементом управления. Адаптеры используются при работе с виджетами, которые дополняют android.widget.AdapterView: ListView, ExpandableListView, GridView, Spinner, Gallery, а также в активности ListActivity и др. Сам AdapterView дополняет android.widget.ViewGroup.",
                        5,
                        100000,
                        R.drawable.surface));

        productList.add(
                new Product(
                        1,
                        "CardView",
                        "Новый компонент CardView появился в Android Lollipop (API 21), но благодаря библиотеке совместимости доступен и для старых устройств. По сути является дальнейшим развитием FrameLayout и позволяет создавать красивую карточку с тенью и закруглёнными углами, который служит контейнером для других компонентов.",
                        5,
                        1000000,
                        R.drawable.serv));

        productList.add(
                new Product(
                        1,
                        "ViewPager",
                        "ViewPager позволяет нам организовать удобный и красивый просмотр данных с возможностью перелистывания влево-вправо. Сам ViewPager отвечает за показ и прокрутку. Но ему нужен еще PagerAdapter, который отвечает за предоставление данных. PagerAdapter – это базовый абстрактный класс, для которого разработчик дописывает реализацию так, как ему надо. Существует распространенная стандартная (частичная) реализация PagerAdapter, которая работает с фрагментами – это FragmentPagerAdapter. Разработчику остается только создать фрагмент и определить кол-во страниц.",
                        5,
                        1000000,
                        R.drawable.kiborg));

        productList.add(
                new Product(
                        1,
                        "Activity",
                        "активная (active или running) — активность находится на переднем плане экрана. Пользователь может взаимодействовать с активным окном;\n" +
                                "приостановленная (paused) — активность потеряла фокус, но все еще видима пользователю. То есть активность находится сверху и частично перекрывает данную активность. Приостановленная активность может быть уничтожена системой в критических ситуациях при нехватке памяти;\n" +
                                "остановленная (stopped) — если данная активность полностью закрыта другой активностью. Она больше не видима пользователю и может быть уничтожена системой, если память необходима для более важного процесса.",
                        5,
                        1000000,
                        R.drawable.planet));

        productList.add(
                new Product(
                        1,
                        "View",
                        "Все элементы интерфейса пользователя в приложении Android создаются с помощью объектов View и ViewGroup. Объект View формирует на экране элемент, с которым пользователь может взаимодействовать. Объект ViewGroup содержит другие объекты View (и ViewGroup) для определения макета интерфейса. Android предоставляет коллекцию подклассов View и ViewGroup, которая включает в себя обычные элементы ввода (такие как кнопки и текстовые поля) и различные модели макет (такие как линейный или относительный макет).",
                        5,
                        1000000,
                        R.drawable.generate));

        adapter = new ProductAdapter(getActivity().getApplicationContext(), productList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
