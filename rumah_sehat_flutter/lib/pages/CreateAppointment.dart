import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/controller/authentication_controller.dart';
import 'package:rumah_sehat_flutter/pages/SignUp.dart';
import 'package:rumah_sehat_flutter/pages/appointment_list.dart';
import '../main.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:rumah_sehat_flutter/Model/DokterModel.dart';
import 'RumahSehatPage.dart';

class CreateAppointmentPage extends StatefulWidget {
  const CreateAppointmentPage({Key? key}) : super(key: key);

  @override
  State<CreateAppointmentPage> createState() => _CreateAppointmentPageState();
}

class _CreateAppointmentPageState extends State<CreateAppointmentPage> {

  Future<List<DokterModel>> fetchDokter() async {
    SharedPreferences prefrences = await SharedPreferences.getInstance();
    var USERNAME = prefrences.getString('username');
    var token = prefrences.getString('token');
    var url = Uri.encodeFull(
        'https://apap-065.cs.ui.ac.id/api/mobile/appointment/create');

    var response = await http.get(Uri.parse(url), headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer $token',
    });

    if (response.statusCode == 200) {
      List<DokterModel> listDokterModel = [];
      var data = json.decode(response.body);
      for (var d in data) {
        if (d != null) {
          listDokterModel.add(DokterModel.fromJson(d));
        }
      }
      return listDokterModel;
    } else {
      throw Exception('Failed to load Dokter');
    }
  }

  late Future<List<DokterModel>> futureDokter;
  @override
  void initState() {
    super.initState();
    futureDokter = fetchDokter();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Buat Appointment"),),
        body: FutureBuilder(
          future: fetchDokter(),
          builder: (context, AsyncSnapshot snapshot) {
            if (!snapshot.hasData || snapshot.data!.length == 0) {
              return Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text(
                      "Tidak ada Dokter yang terdaftar",
                      style: TextStyle(
                          color: Colors.indigo,
                          fontFamily: 'Roboto',
                          fontSize: 20,
                          fontWeight: FontWeight.bold),
                    ),

                    SizedBox(height: 8),
                    Container(
                      height: 40,
                      width: 180,
                      margin: EdgeInsets.only(bottom: 10),
                      decoration: BoxDecoration(
                          color: Colors.blue, borderRadius: BorderRadius.circular(20)),
                      child: ElevatedButton(
                        onPressed: () async {
                          Navigator.of(context).pushReplacement(MaterialPageRoute(
                            builder: (context) => const JadwalAppointmentApp(),
                          ));
                        },
                        child: Text(
                          'Kembali',
                          style: TextStyle(color: Colors.white, fontSize: 16),
                        ),
                      ),
                    )
                  ],
                ),
              );
            } else {
              return ListView.builder(
                  itemCount: snapshot.data!.length,
                  itemBuilder: (context, index) {
                    return Column(
                      children: [
                        Text(
                          'Buat Janji Appointment'
                        ),
                        SizedBox(
                          height: 35,
                        ),
                        Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 20),
                            child: Container(
                                alignment: Alignment.center,
                                padding: const EdgeInsets.all(10),
                                child: Text(
                                  'Registrasi Pasien Baru',
                                  style: TextStyle(fontSize: 20,
                                      fontWeight: FontWeight.bold,
                                      color: Colors.grey[700]),

                                ))),

                      ],
                    );
                  });
            }
          },
        )
    );
  }

}