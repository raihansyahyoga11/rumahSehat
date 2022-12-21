import 'package:flutter/material.dart';

import 'dart:async';
import 'dart:convert';
import 'package:rflutter_alert/rflutter_alert.dart';
import 'package:flutter_html/flutter_html.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/Model/PasienModel.dart';
import 'package:rumah_sehat_flutter/Screen/ProfilePage.dart';
import 'package:quickalert/quickalert.dart';
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
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(
                  left: 15.0, right: 15.0, top: 15, bottom: 15),
              child: TextFormField(
                controller: authenticationController.saldoController,
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Nominal Top Up',
                    hintText: 'Masukkan Nominal'),
              ),
            ),
            Container(
                height: 60,
                width: 350,
               child: Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
                  Container(
                    height: 50,
                    width: 150,
                    margin: EdgeInsets.only(bottom: 5),
                    decoration: BoxDecoration(
                        color: Colors.blue, borderRadius: BorderRadius.circular(18)),
                    child: ElevatedButton(
                      onPressed: () async {
                        int code = await authenticationController.attemptTopUp();
                        if (code == 200) {
                          QuickAlert.show(
                            context: context,
                            type: QuickAlertType.success,
                            text: 'Top Up Berhasil!',
                            width: 260,
                          );
                        } else {
                          Navigator.of(context).pushReplacement(MaterialPageRoute(
                            builder: (context) =>  TopUpPage(),
                          ));
                        }
                      },
                      child: Text(
                        'Konfirmasi',
                        style: TextStyle(color: Colors.white, fontSize: 15),
                      ),

                    ),
                  ),
                  Container(
                    height: 50,
                    width: 150,
                    margin: EdgeInsets.only(bottom: 5),
                    decoration: BoxDecoration(
                        color: Colors.blue, borderRadius: BorderRadius.circular(20)),
                    child: ElevatedButton (onPressed: () {Navigator.push(context,
                        MaterialPageRoute(builder: (context) => ProfilePage())); },
                        style: ButtonStyle(
                          backgroundColor: MaterialStatePropertyAll<Color>(Colors.grey),
                        ),
                        child: Text(
                          'Kembali',
                          style: TextStyle(color: Colors.white, fontSize: 15),
                        )
                    ),
                  ),
                ])
            )
          ],
        ),
      ),
  );
}

