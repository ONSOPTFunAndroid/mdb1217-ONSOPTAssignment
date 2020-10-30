# SOPT 27기 안드로이드 과제

UPDATE DATE : 2020.10.30

## 1주차 과제



1. 1주차 필수과제

   **실행 화면**

<img src="images/Week1_1.jpg" style="zoom: 67%;" /> <img src="images/week1_2.jpg" alt="Week1_1" style="zoom:67%;" /> 

​																	필수과제

<img src="images/week1_3.jpg" alt="KakaoTalk_20201030_221423255_04" style="zoom:67%;" /> <img src= images/week1_4.jpg" alt="KakaoTalk_20201030_221423255_03" style="zoom:67%;"/> 

​															필수과제 + 심화과제1

<img src="images/Week1_5.jpg" alt="KakaoTalk_20201030_221423255_06" style="zoom:67%;" /> <img src="images/Week1_6.jpg" alt="KakaoTalk_20201030_221423255_05" style="zoom:67%;" />  

​																	심화과제2

<video src="C:\Users\다비니PC\Desktop\솝트과제\KakaoTalk_20201030_221424733.mp4"></video>

​																						심화과제2





**주요코드**

- 필수과제 

  

  **MainActivity.kt**

  - 로그인 화면

    ```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
           ...
           button2.setOnClickListener{
               val intent = Intent(this, SignUpActivity::class.java)
               startActivityForResult(intent, 1001)
           }
    }
    ```

    버튼을 누르면 회원 가입 창으로 넘어가게 함

  

  **SignUpActivity.kt**

  - 회원가입 창

    ```kotlin
    override fun onCreate(savedInstanceState: Bundle?) 
           	...
            button.setOnClickListener{
                var name : String = editTextTextPersonName3.text.toString()
                var id : String = editTextTextPersonName4.text.toString()
                var pass : String = editTextTextPersonName5.text.toString()
    
               if(name.isNotEmpty() && id.isNotEmpty() && pass.isNotEmpty()){
            	   	Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
          	     	val intent = Intent(this, MainActivity::class.java)
                   	...
                   	finish()
               }
                else {
                    Toast.makeText(this, "빈 칸이 있습니다", Toast.LENGTH_SHORT).show()
                }
            }
    }
    ```

    isNotEmpty()를 통해 빈 칸의 여부를 파악하고 빈 칸이 있으면, Toast message를 출력함.



- 성장 과제1

  **MainActivity.kt**	

  ```kotlin
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
      super.onActivityResult(requestCode, resultCode, data)
  
      if(resultCode == 1002) {
          if (data != null) {
              editTextTextPersonName.setText(data.extras?.getString("id"))
          }
          if (data != null) {
              editTextTextPersonName2.setText(data.extras?.getString("pass"))
          }
      }
  }
  ```

  onActivityResult함수를 override해서, SignUpActivity에서 받아온 값들을 각각 editText안에 넣어준다.

  

  **SignUpActivity.kt**

  ```kotlin
  override fun onCreate(savedInstanceState: Bundle?) {
          ...
          button.setOnClickListener{
              ...
              if(name.isNotEmpty() && id.isNotEmpty() && pass.isNotEmpty()) {
                  Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                  val intent = Intent(this, MainActivity::class.java)
                  intent.putExtra("name", name)
                  intent.putExtra("id", id)
                  intent.putExtra("pass", pass)
                  setResult(1002, intent)
                  finish()
              }
              ...
          }
      }
  ```

  intent.putExtra를 통해, 각각의 값(이름, id, password)들을 다시 mainActivity로 전달해준다.

  

- 성장 과제2

  **MainActivity.kt**	

  ```kotlin
   private fun saveData() {
          val pref = getSharedPreferences("pref", 0)
          val edit = pref.edit()
          if(editTextTextPersonName.text.toString().isNotEmpty() && editTextTextPersonName2.text.toString().isNotEmpty()) {
              edit.putString("id", editTextTextPersonName.text.toString())
              edit.putString("pass", editTextTextPersonName2.text.toString())
              edit.putBoolean("check", true)
              edit.apply()
          }
      }
  ```

  getSharedPreferences를 통해, id, password, check(로그인에 성공했는 지 여부)의 값들을 저장해준다.

  

  **MainActivity.kt**

  ```kotlin
  private fun loadData() {
      val pref = getSharedPreferences("pref", 0)
      if(pref.getBoolean("check", false)) {
          Toast.makeText(this, "자동로그인 완료", Toast.LENGTH_SHORT).show()
          val intent = Intent(this, SecondActivity::class.java)
          startActivity(intent)
      }
  }
  ```

  check의 값이 false가 아니라면, 자동로그인이 완료되었다는 토스트메시지와 함께 곧장 화면이 전환된다.

  

## 2주차 과제

**실행 화면**



<video src="C:\Users\다비니PC\Desktop\솝트과제\KakaoTalk_20201030_221427912.mp4"></video> 

​																						필수과제


<video src="C:\Users\다비니PC\Desktop\솝트과제\KakaoTalk_20201030_221430653.mp4"></video>

​																						성장과제1

<video src="C:\Users\다비니PC\Desktop\솝트과제\KakaoTalk_20201030_221433410.mp4"></video>

​																						성장과제2



**주요코드**

- 필수과제 

  

  **SampleViewHolder.kt**

  ```kotlin
  override fun onClick(v: View?) {
      val intent = Intent(v?.context, Detailed_Activity::class.java)
      v?.context?.startActivity(intent)
  }
  ```

  view를 클릭할 때마다 특정한 이벤트가 발생하게 하기위해, onClick을 override해준다. 클릭 시, intent를 통해, 세부설명이 있는 창으로 넘어가게 해준다.

  

  **SampleAdapter.kt**

  ```kotlin
  interface ItemClick
  {
      fun onClick(view: View, position: Int)
  }
  
  override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
          holder.onBind(data[position])
          if(itemClick != null)
          {
              holder.itemView.setOnClickListener { v ->
                  itemClick?.onClick(v, position)
              }
          }
      }
  ```

  interface를 구현해, 나중에 activity에서 adapter를 호출 시, onClick을 구현하게끔 해줬다.

  

  **SecondActivity.kt**

  ```kotlin
  class SecondActivity : AppCompatActivity() {
      private lateinit var sampleAdapter: SampleAdapter
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_second)
  
          sampleAdapter = SampleAdapter(this)
  
          sampleAdapter.itemClick = object: SampleAdapter.ItemClick {
              override fun onClick(view: View, position: Int) {
                  val title: TextView = view.findViewById(R.id.item_title);
                  val subTitle: TextView = view.findViewById(R.id.item_subTitle);
                  val intent = Intent(view.context, Detailed_Activity::class.java)
                  intent.putExtra("title", title.text.toString())
                  intent.putExtra("subtitle", subTitle.text.toString())
                  setResult(1003, intent)
                  startActivity(intent)
              }
          }
          ...
  }
  ```

  onClick을 override해서 view에서 title과 subtitle의 값을 가져와, putExtra를 통해, 상세설명 activity로 넘겨줬다.(이 값들이 무엇이냐에따라 어떤 화면이 나올 지 달라지게 된다.)

  

- 심화과제1 

  **SampleAdapter.kt**

  ```kotlin
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
      var view = LayoutInflater.from(context).inflate(R.layout.sample_item_list, parent, false)
      if(viewType == 1) {
          view = LayoutInflater.from(context).inflate(R.layout.grid_item_list, parent, false)
      }
      return SampleViewHolder(view)
  }
  
  fun setItemViewType(sw : Int) {
      changeViewType = sw
      notifyDataSetChanged()
  }
  
  override fun getItemViewType(position: Int) : Int{
      return changeViewType
  }
  ```

  onCreateViewHolder에서 viewType에 따라 inflate해주는 view가 달라지게 해줬다. 그 외에 viewType을 설정하는 setItemViewType과 viewType의 값을 가져오는 getItemviewType을 만들어줬다.

  

  **SampleAdapter.kt**

  ```kotlin
  switch2.setOnCheckedChangeListener{CompoundButton, onSwitch ->
      //  스위치가 켜지면
      if (onSwitch){
          sampleAdapter.setItemViewType(1)
          main_rcv.adapter = sampleAdapter
          main_rcv.layoutManager = GridLayoutManager(this, 2)
      }
      //  스위치가 꺼지면
      else{
          sampleAdapter.setItemViewType(0);
          main_rcv.adapter = sampleAdapter
          main_rcv.layoutManager = LinearLayoutManager(this)
      }
  }
  ```

  스위치버튼을 하나 만들어서 스위치가 켜지면, ViewType을 1(grid layout)으로 만들어줬고, 스위치가 꺼지면  ViewType을 0(linear layout)으로 만들어줬다.



- 심화과제2

  **ItemTouchHelperListener.kt**

  ```kotlin
  interface ItemTouchHelperListener {
      fun onItemMoved(from : Int, to : Int)
      fun onItemSwiped(position : Int)
  }
  ```

  interface를 만들어, 뷰의 아이템들이 이동할 때와, swipe될 때 구현 할 함수들을 미리 정의해놓았다.

  

  **ItemTouchHelperCallback.kt**

  ```kotlin
  class ItemTouchHelperCallback(private val listener: ItemTouchHelperListener) : ItemTouchHelper.Callback(){
  
      override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
          val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
          val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
          return makeMovementFlags(dragFlags, swipeFlags)
      }
  
      override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
          listener.onItemMoved(viewHolder!!.adapterPosition, target!!.adapterPosition)
          return true
      }
  
      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
          listener.onItemSwiped(viewHolder!!.adapterPosition)
      }
  
      override fun isLongPressDragEnabled(): Boolean = true
  }
  ```

  ItemTouchHelper를 쓰기위해 callback에서 getMovementFlags, onMove, onSwiped, isLongPressDragEnabled(길게누르는 거 허용할 지 말지)를 override해줬다.

  

  **SampleAdapter.kt**

  ```kotlin
  override fun onItemMoved(from: Int, to: Int) {
      if (from == to) {
          return
      }
  
      val fromItem = data.removeAt(from)
      data.add(to, fromItem)
      notifyItemMoved(from, to)
  }
  
  override fun onItemSwiped(position: Int) {
      data.removeAt(position)
      notifyItemRemoved(position)
  }
  ```

  SampleAdapter에서 각각 아이템이 이동할 때(위치를 바꿔 줌)와 스와이프 할 때(삭제시켜줌) 실행해야할 함수를 구현하여주었다. 
