import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/Model/PasienModel.dart';
import 'package:rumah_sehat_flutter/controller/authentication_controller.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'TopUpPage.dart';





class ProfilePage extends StatefulWidget {

  @override
  State<ProfilePage> createState() => _ProfilePageState();

}

class _ProfilePageState extends State<ProfilePage> {
  late Future<PasienModel> futureAlbum;


  @override
  void initState() {
    super.initState();
    AuthenticationController profileController= AuthenticationController();
    futureAlbum = AuthenticationController().getUserProfile();

  }


  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: 'Fetch Data Example',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
      body:SizedBox(
          width: double.infinity,
          child:
          FutureBuilder<PasienModel>(
            future: futureAlbum,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
            return ListView(
              padding: const EdgeInsets.all(8),
              children: <Widget>[
                Container(
                  height: 100,
                  child: Center(child: Icon(Icons.male)),
                ),
                Container(
                  height: 50,
                  child: ListTile(
                      title: Text(
                          'Nama',
                           style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 17
                        )
                      ),
                      subtitle: Text(
                        snapshot.data!.nama,
                        style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize:12
                        )
                      )
                  ),
                ),
                Container(
                  height: 50,
                  child: ListTile(
                      title: Text(
                          'Username',
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 17
                          )
                      ),
                      subtitle: Text(
                          snapshot.data!.username,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize:12
                          )
                      )
                  ),
                ),
                Container(
                  height: 50,
                  child: ListTile(
                      title: Text(
                          'Email',
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 17
                          )
                      ),
                      subtitle: Text(
                          snapshot.data!.email,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 12
                          )
                      )
                  ),
                ),
                Container(
                  height: 100,
                  child: ListTile(
                      title: Text(
                          'Saldo',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 20
                          )
                      ),
                      subtitle: Text(
                          snapshot.data!.saldo.toString(),
                          textAlign: TextAlign.center,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 30
                          )
                      )
                  ),
                ),
                Container(
                  width: 60,
                  height: 35,
                  decoration: BoxDecoration(
                      color: Colors.blue, borderRadius: BorderRadius.circular(20)),
                  child: ElevatedButton(
                    child: const Text('Top Up', selectionColor: Color(0xffffffff)),
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Color(0xffF18265),
                    ),
                    onPressed: () { Navigator.push(context,
                      MaterialPageRoute(builder: (context) => TopUpPage()),);
                    },

                  ),
                ),

              ],
             );
            } else if (snapshot.hasError) {
                Text('${snapshot.error}');

             }

            //By default, show a loading spinner.
              return Text('${snapshot.error}');
            }),
        ),
      )
    );
  }

}

