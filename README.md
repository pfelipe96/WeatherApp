# WeatherAPP
Projeto para teste de conhecimento.

## App construido com Dagger2, Retrofit, RxJava/Kotlin, Mockito, Google APi, Gson e Arquitetura MVP.

O principal objetivo da aplicação é consultar o tempo de cidades.

## Como o projeto está estruturado:

- AppWeather
   - api (Retrofit e RxJava/Kotlin) - Retrofit responsável por manipular api e fazer comunição da mesma e RxJava/Kotlin responsável por fazer o gerenciamento do returno da api
      - OpenWeatherMapApi
      
   - data (Gson) - Classes da dados já inicializadas com Gson(Serialize), facilitando a manipulação do restful.
      - MainData
      - OpenWeatherMapData
      - SysData
      - WeatherData
     
   - di (Dagger)
      - component
        - ApplicationComponent - reponsável de fornece as instâncias dos module para classes injetadas
        
      - module
        - ApiModule - Criar uma instância static do Retrofit e RxJava
        - ApplicationModule - Criar instâncias da Application e Gson
        
   - mvp
      - model - package responsável por puxar as informações da api ou persistência
        - MainModel
        - MainModelInterface
        
      - presenter - package responsável por fazer o two-way entre model e view
        - MainPresenter 
        - MainPresenterInterface
   
      - ui - package responsável de configurar os dados vindo do presenter para views
        - MainActivity
        - MainActivityInterface
      
   - MainApplication - Inicialização do Dagger 
   
   
## Estrutura do layout:
  
    - ConstrintLayout
      - CardView
        - fragment (Google Search)
      - FrameLayout
        - LinearLayoutCompat
          - TextView (NameCity)
          - LinearLayoutCompat
            - LinearLayoutCompat
              - TextView (Texto min)
              - TextView (Temperatura min) 
          - LinearLayoutCompat
              - Image (Icon de tempo)
              - TextView (Temperatura agora)
              - TextView (forecast)
          - LinearLayoutCompat
              - TextView (Texto max)
              - TextView (Temperatura max)
        - ConstraitLayout
          - TextView (Humidade)
          - TextView (Set Humidade)
          - TextView (Sunrise)
          - TextView (Set sunrise)
          - TextView (Sunset)
          - TextView (Set sunset)
      - ProgressBar
      - TextView (OBS)
      - TextView (Cation to action)
          
          
        
              
              
              
      

     
 
