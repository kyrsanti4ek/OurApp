package com.example.user.ourapp.weather;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ourapp.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class FragmentTwo extends Fragment {

    TextView textView;
    NodeList nodeList;
    ProgressDialog pDialog;
    String URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";



    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_two, container, false);

        textView = (TextView) rootView.findViewById(R.id.xmlresult);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipelayout);
        final TextView textView = (TextView) rootView.findViewById(R.id.xmlresult);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                        DownloadXML downloadXML = new DownloadXML(URL);
                        downloadXML.start();


                    }
                }, 3000);
            }
        });

        DownloadXML downloadXML = new DownloadXML(URL);
        downloadXML.start();




        return rootView;
    }


    class DownloadXML extends Thread {

        String urlStr;


        public DownloadXML(String urlStr) {
            this.urlStr = urlStr;
        }

        @Override
        public void run() {
            super.run();
            getData();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toStringData();
                }
            });

        }

        public void getData() {

            try {
                URL url = new URL(urlStr);
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document doc = documentBuilder.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
                nodeList = doc.getElementsByTagName("row");


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }

        public void toStringData() {

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            textView.setText(dateFormat.format(date) + "\n" + "\n"); //2016/11/16 12:08:43


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    NamedNodeMap vv = eElement.getChildNodes().item(0).getAttributes();

                    Float rate = Float.parseFloat(vv.getNamedItem("buy").getNodeValue());
                    DecimalFormat df = new DecimalFormat("0.00");
                    df.setMaximumFractionDigits(2);

                    Float rate2 = Float.parseFloat(vv.getNamedItem("sale").getNodeValue());
                    DecimalFormat df2 = new DecimalFormat("0.00");
                    df2.setMaximumFractionDigits(2);

                    textView.setText("\n" + textView.getText() + "Валюта: "

                                    + vv.getNamedItem("ccy").getNodeValue()
                                    + "\n"
                                    + " Курс покупки: " + df.format(rate)
                                    + "\n"
                                    + " Курс продажи: " + df2.format(rate2)

                                    + "\n" + "\n"


                    );
                }

            }



        }


    }
}





