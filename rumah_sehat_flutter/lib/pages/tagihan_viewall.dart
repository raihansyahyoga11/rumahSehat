import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:rumah_sehat_flutter/Model/TagihanModel.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'tagihan_detail.dart';

class TagihanModelPage extends StatefulWidget {
  const TagihanModelPage({Key? key}) : super(key: key);

  @override
  _TagihanModelPageState createState() => _TagihanModelPageState();
}

class _TagihanModelPageState extends State<TagihanModelPage> {
  Future<List<TagihanModel>> fetchTagihanModel() async {
    final storage = new FlutterSecureStorage();
    String? token1 = await storage.read(key: 'TOKEN');
    var url = Uri.parse('https://apap-065.cs.ui.ac.id/api/v1/list-tagihan');
    var response = await http.get(
      url,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer $token1',
      },
    );
    if (response.statusCode == 200) {
    }
    // melakukan decode response menjadi bentuk json
    var data = jsonDecode(utf8.decode(response.bodyBytes));
    // melakukan konversi data json menjadi object TagihanModel
    List<TagihanModel> listTagihanModel = [];
    for (var d in data) {
      if (d != null) {
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
                        "Tidak ada tagihan",
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
                            Text(
                              snapshot.data![index].isPaid
                                  ? "Sudah dibayar"
                                  : "Belum dibayar",
                              style: const TextStyle(
                                  fontSize: 14, color: Colors.black),
                            ),
                            const SizedBox(height: 10),
                            Text(
                                "Tanggal Terbuat: ${snapshot.data![index].tanggalTerbuat}"),
                            const SizedBox(height: 10),
                            Text(
                                "Jumlah Tagihan: ${snapshot.data![index].jumlahTagihan}"),
                            const SizedBox(height: 10),
                            TextButton(
                                onPressed: () {
                                  Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                        builder: (context) =>
                                            TagihanDetailPage(
                                              tagihan: snapshot.data![index],
                                            ),
                                      ));
                                },
                                child: Text('Detail'))
                          ],
                        ),
                      ));
                }
              }
            }));
    ;
  }
}
