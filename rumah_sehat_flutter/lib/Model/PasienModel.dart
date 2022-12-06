import 'dart:async';
import 'dart:convert';
import 'package:flutter_html/flutter_html.dart';
import 'package:http/http.dart' as http;
import 'package:json_annotation/json_annotation.dart';




class PasienModel {
   final String  uuid;
   final String  nama;
   final String  role;
   final String  username;
   final String  password;
   final String  email;
   final int  saldo;
   final int  umur;
   final List<dynamic>?  listAppointment;



   PasienModel({
      required this.uuid,
      required this.nama,
      required this.username,
      required this.email,
      required this.role,
      required this.password,
      required this.umur,
      required this.saldo,
      required this.listAppointment,
   });
   factory PasienModel.fromJson(Map<String, dynamic> json) {
      return PasienModel(
          uuid: json['uuid'],
          nama: json['nama'],
          username: json['username'],
          email: json['email'],
          role: json['role'],
          password: json['password'],
          umur: json['umur'],
          saldo: json['saldo'],
          listAppointment: json['listAppointment']
      );
   }

}