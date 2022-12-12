import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:rumah_sehat_flutter/Model/TagihanModel.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class TagihanModelPage extends StatefulWidget {
  const TagihanModelPage({Key? key}) : super(key: key);

  @override
  _TagihanModelPageState createState() => _TagihanModelPageState();
}

class _TagihanModelPageState extends State<TagihanModelPage> {
  Future<List<TagihanModel>> fetchTagihanModel() async {
    SharedPreferences prefrences = await SharedPreferences.getInstance();
    var token = prefrences.getString('token');
    var url = Uri.parse('http://localhost:8080/api/v1/list-tagihan');
    print("await");
    var response = await http.get(
      url,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer $token',
      },
    );
    print(response.body);
    print("udah get");
    if (response.statusCode == 200) {
      print("berhasil");
    }
    // melakukan decode response menjadi bentuk json
    var data = jsonDecode(utf8.decode(response.bodyBytes));
    print("udah decode");
    print(data);
    // melakukan konversi data json menjadi object TagihanModel
    List<TagihanModel> listTagihanModel = [];
    for (var d in data) {
      print(d);
      if (d != null) {
        print(TagihanModel.fromJson(d));
        listTagihanModel.add(TagihanModel.fromJson(d));
      }
    }

    return listTagihanModel;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('List Tagihan'),
        ),
        drawer: Drawer(
          child: Column(
            children: [
              ListTile(
                title: const Text('TagihanModel'),
                onTap: () {
                  // Route menu ke halaman to do
                  Navigator.pushReplacement(
                    context,
                    MaterialPageRoute(
                        builder: (context) => const TagihanModelPage()),
                  );
                },
              ),
            ],
          ),
        ),
        body: FutureBuilder(
            future: fetchTagihanModel(),
            builder: (context, AsyncSnapshot snapshot) {
              if (snapshot.data == null) {
                return const Center(child: CircularProgressIndicator());
              } else {
                if (!snapshot.hasData) {
                  return Column(
                    children: const [
                      Text(
                        "Tidak ada to do list :(",
                        style:
                            TextStyle(color: Color(0xff59A5D8), fontSize: 20),
                      ),
                      SizedBox(height: 8),
                    ],
                  );
                } else {
                  return ListView.builder(
                      itemCount: snapshot.data!.length,
                      itemBuilder: (_, index) => Container(
                            margin: const EdgeInsets.symmetric(
                                horizontal: 16, vertical: 12),
                            padding: const EdgeInsets.all(20.0),
                            decoration: BoxDecoration(
                                color: Colors.white,
                                borderRadius: BorderRadius.circular(15.0),
                                boxShadow: const [
                                  BoxShadow(
                                      color: Colors.black, blurRadius: 2.0)
                                ]),
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.start,
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  "Kode Tagihan: ${snapshot.data![index].kode}",
                                  style: const TextStyle(
                                    fontSize: 18.0,
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                                const SizedBox(height: 10),
                                Text("Status: ${snapshot.data![index].isPaid}"),
                                const SizedBox(height: 10),
                                Text(
                                    "Tanggal Terbuat: ${snapshot.data![index].tanggalTerbuat}"),
                                const SizedBox(height: 10),
                                Text(
                                    "Jumlah Tagihan: ${snapshot.data![index].jumlahTagihan}"),
                              ],
                            ),
                          ));
                }
              }
            }));
    ;
  }
}