import 'dart:async';
import 'dart:convert';
import 'package:flutter_html/flutter_html.dart';
import 'package:http/http.dart' as http;
import 'package:json_annotation/json_annotation.dart';

class DokterModel {
  final String  uuid;
  final String  nama;
  final String  role;
  final String  username;
  final String  password;
  final String  email;
  final int  tarif;

  DokterModel({
    required this.uuid,
    required this.nama,
    required this.username,
    required this.email,
    required this.role,
    required this.password,
    required this.tarif,
  });
  factory DokterModel.fromJson(Map<String, dynamic> json) {
    return DokterModel(
      uuid: json['uuid'],
      nama: json['nama'],
      username: json['username'],
      email: json['email'],
      role: json['role'],
      password: json['password'],
      tarif: json['tarif'],
    );
  }
}