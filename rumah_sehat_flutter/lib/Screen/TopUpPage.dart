import 'package:flutter/material.dart';

import 'dart:async';
import 'dart:convert';
import 'package:rflutter_alert/rflutter_alert.dart';
import 'package:flutter_html/flutter_html.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/Model/PasienModel.dart';
import 'package:rumah_sehat_flutter/Screen/ProfilePage.dart';

import '../controller/authentication_controller.dart';


class TopUpPage extends StatelessWidget{
  AuthenticationController authenticationController = AuthenticationController();
  @override
  Widget build(BuildContext context) => Scaffold(
      body:
      SingleChildScrollView(
        child: Column(
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.only(top: 60.0),
              child: Center(
                child: Container(
                  width: 200,
                  height: 150,
                  /*decoration: BoxDecoration(
                        color: Colors.red,
                        borderRadius: BorderRadius.circular(50.0)),*/
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(
                  left: 15.0, right: 15.0, top: 15, bottom: 15),
              //padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextFormField(
                controller: authenticationController.saldoController,
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Nominal Top Up',
                    hintText: 'Masukkan Nominal'),
              ),
            ),
            Container(
              height: 50,
              width: 250,
              margin: EdgeInsets.only(bottom: 10),
              decoration: BoxDecoration(
                  color: Colors.blue, borderRadius: BorderRadius.circular(20)),
              child: ElevatedButton(
                onPressed: () async {
                  int code = await authenticationController.attemptTopUp();
                  if (code == 200) {
                    sweatAlert(context);
                  } else {
                    Navigator.of(context).pushReplacement(MaterialPageRoute(
                      builder: (context) =>  TopUpPage(),
                    ));
                  }
                },
                child: Text(
                  'Konfirmasi',
                  style: TextStyle(color: Colors.white, fontSize: 25),
                ),

              ),
            ),
            ElevatedButton (onPressed: () {Navigator.push(context,
                MaterialPageRoute(builder: (context) => ProfilePage())); },
                child: Text(
                  'Kembali',
                  style: TextStyle(color: Colors.white, fontSize: 25),
                )
            ),

          ],
        ),
      ),
  );
  void sweatAlert(BuildContext context) {
    Alert(
      context: context,
      type: AlertType.success,
      title: "TopUp berhasil",
      desc: "TopUp telah berhasil silahkan tekan kembali",
    ).show();
    return;
  }

}

