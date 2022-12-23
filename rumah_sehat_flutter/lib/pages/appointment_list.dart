// ignore_for_file: prefer_interpolation_to_compose_strings, prefer_const_constructors

import 'dart:convert';
import 'package:intl/intl.dart';
import 'package:rumah_sehat_flutter/Model/appointment_model.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'appointment_detail.dart';
import 'package:rumah_sehat_flutter/pages/CreateAppointment.dart';

class JadwalAppointmentApp extends StatefulWidget {
  const JadwalAppointmentApp({Key? key}) : super(key: key);

  @override
  State<JadwalAppointmentApp> createState() => _JadwalAppointmentState();
}

class _JadwalAppointmentState extends State<JadwalAppointmentApp> {
  Future<List<Appointment>> fetchAppointment() async {
    SharedPreferences prefrences = await SharedPreferences.getInstance();
    var token = prefrences.getString('token');
    var url = Uri.encodeFull(
        'https://apap-065.cs.ui.ac.id/api/mobile/appointment/list-appointment/');

    var response = await http.get(Uri.parse(url), headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer $token',
    });

    if (response.statusCode == 200) {
      List<Appointment> listAppointment = [];
      var data = jsonDecode(utf8.decode(response.bodyBytes));
      for (var d in data) {
        if (d != null) {
          listAppointment.add(Appointment.fromJson(d));
        }
      }
      return listAppointment;
    } else {
      throw Exception('Failed to load appointment');
    }
  }

  late Future<List<Appointment>> futureAppointment;
  @override
  void initState() {
    super.initState();
    futureAppointment = fetchAppointment();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: FutureBuilder(
            future: fetchAppointment(),
            builder: (context, AsyncSnapshot snapshot) {
              if (!snapshot.hasData || snapshot.data!.length == 0) {
                return Center(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        "Anda belum memiliki Appointment",
                        textAlign: TextAlign.center,
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
                                builder: (context) => CreateAppointmentPage(),
                            ));
                            // int code = await authenticationController.loginUser();
                            // if (code == 200) {
                            //   Navigator.of(context).pushReplacement(MaterialPageRoute(
                            //     builder: (context) =>  RumahSehatMain(),
                            //   ));
                            // } else {
                            //   showDialog(
                            //       context: context,
                            //       builder: (BuildContext context) {
                            //         return AlertDialog(
                            //           title: Text("Error"),
                            //           content: Text("Bad Credential, please try again"),
                            //           actions: [
                            //             ElevatedButton(
                            //               child: Text("Ok"),
                            //               onPressed: () {
                            //                 Navigator.of(context).pop();
                            //               },
                            //             )
                            //           ],
                            //         );
                            //       });
                            // }
                          },
                          child: Text(
                            'Buat Appointment',
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
                      return Card(
                        child: Padding(
                          padding: EdgeInsets.all(12.0),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: <Widget>[
                              Text(
                                snapshot.data![index].kode,
                                style: TextStyle(
                                  fontSize: 20,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                              Text(
                                  "Dokter: " +
                                      snapshot.data![index].dokter.nama,
                                  style: TextStyle(
                                      fontFamily: 'Roboto',
                                      fontSize: 16,
                                      color: Colors.grey)),
                              Text(
                                  "Waktu awal: " +
                                      (DateFormat('d MMM yyy HH:mm:ss')
                                          .format(
                                              snapshot.data![index].waktuAwal)
                                          .toString()),
                                  style: TextStyle(
                                      fontFamily: 'Roboto',
                                      fontSize: 16,
                                      color: Colors.grey)),
                              Text(
                                  "Status: " +
                                      (snapshot.data![index].isDone
                                          ? "Sudah selesai"
                                          : "Belum selesai"),
                                  style: TextStyle(
                                      fontFamily: 'Roboto',
                                      fontSize: 16,
                                      color: Colors.grey)),
                              SizedBox(
                                height: 12,
                              ),
                              TextButton(
                                  onPressed: () {
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                          builder: (context) =>
                                              AppointmentDetail(
                                            appointment: snapshot.data![index],
                                          ),
                                        ));
                                  },
                                  child: Text('View Detail'))
                            ],
                          ),
                        ),
                      );
                    });
              }
            }),
    );
  }
}
