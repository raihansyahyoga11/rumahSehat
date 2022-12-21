import 'package:rumah_sehat_flutter/controller/authentication_controller.dart';
import 'package:rumah_sehat_flutter/pages/appointment_list.dart';

import '../Screen/ProfilePage.dart';
import '../main.dart';
import 'package:rumah_sehat_flutter/pages/RumahSehatPage.dart';

import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {

  @override
  State<HomePage> createState() => _HomePage();
}

class _HomePage extends State<HomePage> {

  AuthenticationController authenticationController = AuthenticationController();

  @override
  Widget build(BuildContext context) => Scaffold(
    body: Center(
      child: Container(
        height: 500,
        width: 300,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Image.asset('assets/hospital.png'),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () async {
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) => JadwalAppointmentApp()));
                  },
                  child: Text(
                    'Daftar Appointment',
                    style: TextStyle(color: Colors.white, fontSize: 25),
                  ),
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () async {
                    authenticationController.logOut();
                    Navigator.of(context).pushReplacement(MaterialPageRoute(
                      builder: (context) => Login(),
                    ));
                  },
                  child: Text(
                    'Log out',
                    style: TextStyle(color: Colors.white, fontSize: 25),
                  ),
                ),
              ],
            ),
          ],

        ),
      ),
    ),
  );
}
