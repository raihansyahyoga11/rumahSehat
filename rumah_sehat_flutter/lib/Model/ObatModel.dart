import 'dart:async';
import 'dart:convert';
import 'package:flutter_html/flutter_html.dart';
import 'package:http/http.dart' as http;
import 'package:json_annotation/json_annotation.dart';

class ObatModel {
  final String id;
  final String nama;
  final int stok;
  final int harga;
  final List<dynamic> listJumlah;

  ObatModel({
    required this.id,
    required this.nama,
    required this.stok,
    required this.harga,
    required this.listJumlah,
  });
  factory ObatModel.fromJson(Map<String, dynamic> json) {
    return ObatModel(
        id: json['id'],
        nama: json['nama'],
        stok: json['stok'],
        harga: json['harga'],
        listJumlah: json['listJumlah']);
  }
}
