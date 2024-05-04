package com.example.appmusic

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appmusic.databinding.ActivityAdminTrackBinding
import com.example.appmusic.databinding.HeaderMenuBinding
import com.google.android.material.navigation.NavigationView

class AdminTrackActivity : AppCompatActivity() {
    lateinit var binding:ActivityAdminTrackBinding
    lateinit var listTrack: ArrayList<Tracks>
    lateinit var trackAdapter:TrackAdapter
    val genres: List<String> = listOf("Lãng Mạn", "Bolero", "Rock", "Thiếu Nhi", "Lofi", "Rap", "Buồn", "Tình Yêu", "Trữ Tình", "Nhạc Trẻ");
    val countries: List<String> = listOf("Việt Nam", "Hoa", "Hàn Quốc", "Âu Mỹ", "Nhật Bản");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminTrackBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//menu
        // Khai báo drawerLayout và navigationView
        val drawer = findViewById<DrawerLayout>(R.id.admin)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        // Lấy dữ liệu người dùng từ Intent
        val adminName = intent.getStringExtra("admin_name")

        // Gán headerLayoutBinding cho navigationView menu
        val headerLayoutBinding = HeaderMenuBinding.bind(navigationView.getHeaderView(0))
        headerLayoutBinding.menuUsername.text = "Admin: " + adminName

        // Thiết lập listener cho các mục menu trong navigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.admin_user -> {
                    val intent = Intent(this, AdminUserActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.admin_song -> {
                    val intent = Intent(this, AdminTrackActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.admin_chart -> {
                    val intent = Intent(this, AdminActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.admin_logout -> {
                    val intent = Intent(this, AdminLogin::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        // Xử lý sự kiện khi người dùng nhấn nút mở Drawer
        binding.btnOpenDrawer.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

//main
        val dbHelper = DatabaseTrack(this)
        // Truy vấn cơ sở dữ liệu để lấy danh sách bài hát
        listTrack = dbHelper.getAllTracks()

        val genreAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genres)
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SpinnerAdminTrackGenre.adapter = genreAdapter

        val countryAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries)
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SpinnerAdminTrackCountry.adapter = countryAdapter

        binding.SpinnerAdminTrackGenre.setSelection(0) // Chọn phần tử đầu tiên làm mặc định
        binding.SpinnerAdminTrackCountry.setSelection(0) // Chọn phần tử đầu tiên làm mặc định

        // Khởi tạo adapter và gán adapter cho ListView
        trackAdapter = TrackAdapter(this, listTrack, genres, countries)
        binding.listviewTrack.adapter = trackAdapter

        // Xử lý sự kiện khi chọn phần tử trong Spinner
        binding.SpinnerAdminTrackGenre.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedGenre = parent.getItemAtPosition(position).toString()
                // Thực hiện các hành động dựa trên thể loại được chọn
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Không làm gì
            }
        }

        binding.SpinnerAdminTrackCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCountry = parent.getItemAtPosition(position).toString()
                // Thực hiện các hành động dựa trên quốc gia được chọn
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Không làm gì
            }
        }

        // Xử lý sự kiện thêm bài hát mới
        binding.btnAdd.setOnClickListener {
            val trackName = binding.edtAdminTrackName.text.toString()
            val trackArtist = binding.edtAdminTrackArtist.text.toString()
            val trackGenre = binding.SpinnerAdminTrackGenre.selectedItem.toString() // Sử dụng selectedItem.toString() để lấy giá trị được chọn
            val trackCountry = binding.SpinnerAdminTrackCountry.selectedItem.toString() // Sử dụng selectedItem.toString() để lấy giá trị được chọn
            val trackView = binding.edtAdminTrackView.text.toString()
            val trackReleaseDate = binding.edtAdminTrackReleaseDay.text.toString()
            val trackImg = binding.edtAdminTrackImg.text.toString()

            // Kiểm tra thông tin nhập vào
            if (TextUtils.isEmpty(trackName) || TextUtils.isEmpty(trackArtist) || TextUtils.isEmpty(trackGenre)
                || TextUtils.isEmpty(trackCountry) || TextUtils.isEmpty(trackView) || TextUtils.isEmpty(trackReleaseDate)
                || TextUtils.isEmpty(trackImg)) {
                showResultDialog("Vui lòng điền đầy đủ thông tin bài hát!")
            } else {
                // Thực hiện thêm mới bài hát vào cơ sở dữ liệu
                val dbHelper = DatabaseTrack(this)
                val isInserted = dbHelper.insert(trackName, trackArtist, trackGenre,trackView, trackCountry,trackImg, trackReleaseDate)
                dbHelper.close()

                if (isInserted) {
                    // Nếu insert thành công
                    binding.edtAdminTrackName.setText("")
                    binding.edtAdminTrackArtist.setText("")
                    binding.SpinnerAdminTrackGenre.setSelection(0)
                    binding.SpinnerAdminTrackCountry.setSelection(0)
                    binding.edtAdminTrackView.setText("")
                    binding.edtAdminTrackReleaseDay.setText("")
                    binding.edtAdminTrackImg.setText("")
                    trackAdapter.notifyDataSetChanged()
                    showResultDialog("Thêm bài hát thành công!")
                }
            }
        }

        binding.btnSearch.setOnClickListener {
//            showSearchDialog()
        }

    // Xử lý sự kiện xóa bài hát khi giữ dài trên một mục trong ListView
        binding.listviewTrack.setOnItemLongClickListener { parent, view, position, id ->
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Xác nhận xóa")
            alertDialog.setMessage("Bạn muốn xóa hay không ?")
            alertDialog.setPositiveButton("Có") { dialog, which ->
                val track = listTrack[position] // Lấy người dùng từ danh sách nguồn
                val dbHelper = DatabaseTrack(this) // Khởi tạo đối tượng Database
                val isDeleted = dbHelper.deleteTrack(track) // Gọi phương thức delete từ Database để xóa dữ liệu

                if (isDeleted) {
                    // Xóa bài hát từ danh sách nguồn và cập nhật ListView
                    listTrack.removeAt(position)
                    trackAdapter.notifyDataSetChanged()
                    showResultDialog("Xóa dữ liệu thành công!")
                } else {
                    showResultDialog("Xóa dữ liệu không thành công!")
                }
            }

            alertDialog.setNegativeButton("Không") { dialog, which ->
                showResultDialog("Xóa dữ liệu không thành công!")
            }

            alertDialog.create().show()
            return@setOnItemLongClickListener false
        }

    }

    // Xử lý sự kiện hiện ra 1 dialog search thông tin
//    private fun showSearchDialog() {
//        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_search_track, null)
//        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
//            .setView(dialogView)
//            .setTitle("Tìm kiếm")
//            .setPositiveButton("Tìm") { dialog, which ->
//                var TrackIdSearch = dialogView.findViewById<EditText>(R.id.edtAdminTrackID)
//                var AdminTrackName = dialogView.findViewById<EditText>(R.id.edtAdminTrackName)
//                var AdminTrackArtist = dialogView.findViewById<EditText>(R.id.edtAdminTrackArtist)
//                var AdminTrackCountry = dialogView.findViewById<Spinner>(R.id.edtAdminTrackCountry)
//                var AdminTrackGenre = dialogView.findViewById<Spinner>(R.id.edtAdminTrackGenre)
//                var AdminTrackReleaseDay = dialogView.findViewById<EditText>(R.id.edtAdminTrackReleaseDay)
//                var AdminTrackView = dialogView.findViewById<EditText>(R.id.edtAdminTrackView)
//
//                val edtTrackIdSearch = TrackIdSearch.text.toString().trim()
//                val edtAdminTrackName = AdminTrackName.text.toString().trim()
//                val edtAdminTrackArtist = AdminTrackArtist.text.toString().trim()
//                val edtAdminTrackCountry = AdminTrackCountry.selectedItem.toString().trim()
//                val edtAdminTrackGenre = AdminTrackGenre.selectedItem.toString().trim()
//                val edtAdminTrackReleaseDay = AdminTrackReleaseDay.text.toString().trim()
//                val edtAdminTrackView = AdminTrackView.text.toString().trim()
//
//                searchTrack(edtTrackIdSearch, edtAdminTrackName, edtAdminTrackArtist, edtAdminTrackCountry,edtAdminTrackGenre, edtAdminTrackReleaseDay, edtAdminTrackView)
//            }
//            .setNegativeButton("Hủy") { dialog, which ->
//                dialog.dismiss()
//            }
//
//        val dialog = dialogBuilder.create()
//        dialog.show()
//    }
//
//    private fun searchTrack(TrackIDSearch: String, TrackNameSearch: String, TrackArtistSearch: String,
//                           TrackCountrySearch: String, TrackGenreSearch: String, TrackReleaseDaySearch: String, TrackViewSearch: String) {
//        val searchList = ArrayList<Tracks>()
//
//        for (track in listTrack) {
//            if (track.trackID.contains(TrackIDSearch, ignoreCase = true) &&
//                track.trackName.contains(TrackNameSearch, ignoreCase = true) &&
//                track.trackArtist.contains(TrackArtistSearch, ignoreCase = true) &&
//                track.trackCountry.contains(TrackCountrySearch, ignoreCase = true) &&
//                track.trackGenre.contains(TrackGenreSearch, ignoreCase = true) &&
//                track.trackReleaseDate.contains(TrackReleaseDaySearch, ignoreCase = true) &&
//                track.trackView.contains(TrackViewSearch, ignoreCase = true)
//            ) {
//                searchList.add(track)
//            }
//        }
//
//        trackAdapter = TrackAdapter(this, listTrack, genres, countries)
//        binding.listviewTrack.adapter = trackAdapter
//
//        showResultDialog("Tìm thấy ${searchList.size} kết quả")
//    }


    // Hàm hiển thị dialog kết quả
    private fun showResultDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                recreate() // Tạo lại hoạt động hiện tại
            }
            .setCancelable(false)
        val dialog = builder.create()
        dialog.show()
    }
}
