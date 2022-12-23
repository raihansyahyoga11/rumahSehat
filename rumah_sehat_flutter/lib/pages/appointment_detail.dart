// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'dart:convert';

import 'package:intl/intl.dart';
import 'package:rumah_sehat_flutter/Model/appointment_model.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/pages/resep_detail.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AppointmentDetail extends StatelessWidget {
  final Appointment appointment;

  const AppointmentDetail({Key? key, required this.appointment})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    final DateFormat formatter = DateFormat('MMM dd, yyyy');
    return Scaffold(
      appBar: AppBar(
        title: const Text('Detail'),
      ),
      body: Row(
        children: <Widget>[
          Expanded(
            flex: 1,
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                children: <Widget>[
                  Column(
                    children: [
                      Text(
                        appointment.kode,
                        style: const TextStyle(
                            fontSize: 24, fontWeight: FontWeight.bold),
                        textAlign: TextAlign.center,
                      ),
                      const SizedBox(height: 20),
                      ListTile(
                          title: const Padding(
                            padding: EdgeInsets.only(bottom: 10.0),
                            child: Text(
                              'Kode: ',
                              style: TextStyle(
                                  fontSize: 16, fontWeight: FontWeight.bold),
                            ),
                          ),
                          dense: true,
                          subtitle: Text(
                            appointment.kode,
                            style: const TextStyle(
                                fontSize: 14, color: Colors.black),
                          )),
                      ListTile(
                          title: const Padding(
                            padding: EdgeInsets.only(bottom: 10.0),
                            child: Text(
                              'Waktu Awal: ',
                              style: TextStyle(
                                  fontSize: 16, fontWeight: FontWeight.bold),
                            ),
                          ),
                          dense: true,
                          subtitle: Text(
                            appointment.waktuAwal.toString(),
                            style: const TextStyle(
                                fontSize: 14, color: Colors.black),
                          )),
                      ListTile(
                        title: const Padding(
                          padding: EdgeInsets.only(bottom: 10.0),
                          child: Text(
                            'Status: ',
                            style: TextStyle(
                                fontSize: 16, fontWeight: FontWeight.bold),
                          ),
                        ),
                        subtitle: Text(
                          appointment.isDone
                              ? "Sudah selesai"
                              : "Belum selesai",
                          style: const TextStyle(
                              fontSize: 14, color: Colors.black),
                        ),
                        dense: true,
                      ),
                      ListTile(
                          title: const Padding(
                            padding: EdgeInsets.only(bottom: 10.0),
                            child: Text(
                              'Dokter: ',
                              style: TextStyle(
                                  fontSize: 16, fontWeight: FontWeight.bold),
                            ),
                          ),
                          dense: true,
                          subtitle: Text(
                            appointment.dokter.nama,
                            style: const TextStyle(
                                fontSize: 14, color: Colors.black),
                          )),
                    ],
                  ),
                  const SizedBox(
                    height: 10,
                  ),
                  SizedBox(
                    width: double.infinity,
                    child: 
                    TextButton(
                      style: const ButtonStyle(
                        alignment: Alignment.centerRight,
                      ),
                      child: const Text(
                        "Lihat Resep",
                        style: TextStyle(
                            fontFamily: 'Roboto',
                            fontSize: 12,
                            color: Colors.indigo),
                      ),
                      onPressed: () {
                        ///
                      },
                    ),
                  ),
                  const Spacer(),
                  TextButton(
                    style: TextButton.styleFrom(
                        backgroundColor: Colors.grey,
                        padding: const EdgeInsets.all(15.0),
                        tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                        alignment: Alignment.center),
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    child: const Text(
                      "Back",
                      style: TextStyle(color: Colors.white),
                    ),
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
