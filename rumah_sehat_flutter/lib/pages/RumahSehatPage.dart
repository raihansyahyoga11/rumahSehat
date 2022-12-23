import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/pages/appointment_list.dart';
import 'package:rumah_sehat_flutter/pages/tagihan_viewall.dart';
import 'package:rumah_sehat_flutter/pages/CreateAppointment.dart';
import '../Screen/ProfilePage.dart';
import 'HomePage.dart';

class RumahSehat extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Rumah Sehat",
      debugShowCheckedModeBanner: false,
      home: RumahSehatMain(),
    );
  }
}

class RumahSehatMain extends StatefulWidget {
  const RumahSehatMain ({Key? key}) : super(key: key);
  @override
  _RumahSehatMainState createState() => _RumahSehatMainState();
}

class _RumahSehatMainState extends State<RumahSehatMain> {
  int _selectedNavbar = 0;
  final screens=[
    HomePage(),
    JadwalAppointmentApp(),
    TagihanModelPage(),
    ProfilePage(),
  ];

  void _changeSelectedNavBar(int index) {
    setState(() {
      _selectedNavbar = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Rumah Sehat"),

      ),
      body: screens[_selectedNavbar],
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Beranda',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.assignment),
            label: 'Appointment',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.assignment ),
            label: 'Tagihan',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.account_circle_outlined),
            label: 'Akun',
          ),
        ],
        currentIndex: _selectedNavbar,
        selectedItemColor: Colors.blue,
        unselectedItemColor: Colors.grey,
        showUnselectedLabels: true,
        onTap: _changeSelectedNavBar,
      ),
    );
  }
}