package com.devathon.slytherin.config;

import com.devathon.slytherin.models.*;
import com.devathon.slytherin.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MagicObjectRepository magicObjectRepository;
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final HouseRepository houseRepository;
    private final CategoryRepository categoryRepository;
    private final RarityRepository rarityRepository;

    public DataSeeder(UserRepository userRepository,
                      MagicObjectRepository magicObjectRepository,
                      SaleRepository saleRepository,
                      SaleItemRepository saleItemRepository,
                      HouseRepository houseRepository,
                      CategoryRepository categoryRepository,
                      RarityRepository rarityRepository) {
        this.userRepository = userRepository;
        this.magicObjectRepository = magicObjectRepository;
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.houseRepository = houseRepository;
        this.categoryRepository = categoryRepository;
        this.rarityRepository = rarityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (houseRepository.count() == 0) {
            // 1. Crear casas
            HouseModel sanDamian = houseRepository.save(
                    HouseModel.builder()
                            .name("SAN DAMIAN")
                            .build());

            HouseModel slytherin = houseRepository.save(
                    HouseModel.builder()
                            .name("SLYTHERIN")
                            .build());

            // 2. Crear usuarios asociados a casas
            UserModel user1 = userRepository.save(
                    UserModel.builder()
                            .name("Harry Potter")
                            .price_galeon(50)
                            .price_sickle(30)
                            .price_knut(20)
                            .build());

            UserModel user2 = userRepository.save(
                    UserModel.builder()
                            .name("Draco Malfoy")
                            .price_galeon(40)
                            .price_sickle(25)
                            .price_knut(15)
                            .build());
            // 3. Crear categorias de objetos mágicos
            CategoryModel artefactos_magicos = categoryRepository.save(
                    CategoryModel.builder()
                            .name("Artefáctos Mágicos")
                            .build());

            CategoryModel ingredientes_para_pociones = categoryRepository.save(
                    CategoryModel.builder()
                            .name("Ingredientes para pociones")
                            .build());

            CategoryModel libros_prohibidos = categoryRepository.save(
                    CategoryModel.builder()
                            .name("Libros Prohibidos")
                            .build());

            CategoryModel criaturas = categoryRepository.save(
                    CategoryModel.builder()
                            .name("Criaturas")
                            .build());

            CategoryModel vestimenta_y_accesorios = categoryRepository.save(
                    CategoryModel.builder()
                            .name("Vestimenta y Accesorios")
                            .build());

            // 3. Crear rarezas de objetos mágicos
            RarityModel comun = rarityRepository.save(
                    RarityModel.builder()
                            .name("Común")
                            .build());
            RarityModel epico = rarityRepository.save(
                    RarityModel.builder()
                            .name("Épico")
                            .build());
            RarityModel legendario = rarityRepository.save(
                    RarityModel.builder()
                            .name("Legendario")
                            .build());

            // 4. Crear objetos mágicos (sin relación)
            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("ESPEJO DE MALDICIONES")
                            .short_description(
                                    "Refleja la maldición que habita en quien lo observa.")
                            .long_description(
                                    "Objeto encantado capaz de mostrar visiones relacionadas con maldiciones activas o potenciales. Utilizado en diagnósticos oscuros, rituales y prácticas de adivinación. Puede emitir susurros o alterar la imagen reflejada sin aviso previo. Debe ser cubierto al almacenarse para evitar manifestaciones indeseadas.")
                            .category(artefactos_magicos)
                            .rarity(comun)
                            .price_galeon(3)
                            .price_sickle(12)
                            .price_knut(7)
                            .url_image("https://i.imgur.com/LqsTrZv.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("CAJA DE LAMENTOS")
                            .short_description(
                                    "Libera susurros, gritos y llantos sellados por la magia oscura.")
                            .long_description(
                                    "Contenedor maldito que encierra fragmentos de energía emocional residual. Al abrirla, emite sonidos perturbadores que afectan la estabilidad mental del entorno. Usada como herramienta de intimidación o en rituales de contacto espiritual. Debe mantenerse cerrada durante más de veintitrés horas para recargar su efecto.")
                            .category(artefactos_magicos)
                            .rarity(comun)
                            .price_galeon(5)
                            .price_sickle(2)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/4wnzk4O.jpeg")
                            .purchased(false)
                            .build());
            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("RELOJ DE ARENA MALDITO")
                            .short_description(" No mide el tiempo. Lo consume.")
                            .long_description(
                                    "Artefacto encantado con magia oscura que drena energía vital durante su uso. Cada giro activa un ciclo de diez minutos en los que el portador siente una pérdida gradual de fuerza. Utilizado en rituales de transferencia mágica o castigos silenciosos. Fabricado con arena negra y cristal encantado irrompible bajo condiciones normales.")
                            .category(artefactos_magicos)
                            .rarity(epico)
                            .price_galeon(9)
                            .price_sickle(18)
                            .price_knut(3)
                            .url_image("https://i.imgur.com/Ons7DQg.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("MANO DE GLORIA")
                            .short_description(
                                    "Ilumina solo para quien la porta. Ideal para incursiones discretas.")
                            .long_description(
                                    "Reliquia oscura fabricada a partir de la mano de un criminal ejecutado. Al sostener una vela encantada, su luz solo es visible para el portador, permitiendo desplazamientos sigilosos en áreas protegidas o sin iluminación. Altamente codiciada entre contrabandistas mágicos y espías del submundo. Extremadamente rara y restringida por el Ministerio.")
                            .category(artefactos_magicos)
                            .rarity(legendario)
                            .price_galeon(32)
                            .price_sickle(5)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/ymwQRIr.jpeg")
                            .purchased(false)
                            .build());
            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("COLGANTE DE OPALINA MALDITO")
                            .short_description(
                                    "Atrae la desgracia con una elegancia letal.")
                            .long_description(
                                    "Amuleto de origen incierto, fabricado con opalina encantada y sellado con un vínculo maldito. Quien lo porta experimenta un incremento gradual de mala suerte en su entorno, especialmente en vínculos personales y decisiones críticas. Utilizado por ciertos magos oscuros para sabotear a sus enemigos sin levantar sospechas. Su efecto se intensifica si el portador desconoce su naturaleza.")
                            .category(artefactos_magicos)
                            .rarity(comun)
                            .price_galeon(6)
                            .price_sickle(9)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/Z1WoInp.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("DOXY")
                            .short_description(
                                    "Criatura alada de carácter agresivo, empleada en encantamientos disuasorios y trampas mágicas.")
                            .long_description(
                                    "El Doxy, también conocido como Hada Mordedora, es una criatura pequeña de aspecto similar a una hada, pero con una doble hilera de afilados dientes venenosos. Se utiliza en la elaboración de pociones tóxicas de bajo nivel, encantamientos de protección no letal y como elemento intimidante en trampas mágicas defensivas. Requiere una jaula encantada para su contención y dosis regulares de poción paralizante para su manejo seguro. Algunos coleccionistas oscuros lo utilizan como mascota simbólica, aunque está prohibido en la mayoría de territorios mágicos.")
                            .category(criaturas)
                            .rarity(comun)
                            .price_galeon(9)
                            .price_sickle(12)
                            .price_knut(5)
                            .url_image("https://i.imgur.com/HU9pHlQ.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("CHIZPURFLE")
                            .short_description(
                                    "Parásito mágico que se alimenta de hechizos, ideal para experimentos de absorción mágica.")
                            .long_description(
                                    "El Chizpurfle es un diminuto parásito mágico con apariencia de garrapata que se siente atraído por objetos encantados y hechizos activos. Se le encuentra comúnmente en varitas, calderos o incluso en mascotas mágicas mal cuidadas. En el mercado tenebroso, se valora por su capacidad para absorber lentamente la magia de objetos protegidos, facilitando su desactivación o deterioro progresivo. Criarlo con fines experimentales requiere vigilancia mágica constante, ya que puede infestar artefactos importantes si no se controla adecuadamente.")
                            .category(criaturas)
                            .rarity(comun)
                            .price_galeon(6)
                            .price_sickle(9)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/gm9h6Ha.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("SNIDGET DORADO")
                            .short_description(
                                    "Ave mágica extremadamente rara y veloz, símbolo de prestigio y fuente de ingredientes prohibidos.")
                            .long_description(
                                    "El Snidget Dorado es una criatura alada de vuelo impredecible y gran velocidad, considerada extinta en muchos registros oficiales. Su captura está prohibida por ley, pero algunos ejemplares sobreviven en colecciones privadas del mercado oscuro. Se cree que su corazón, alado y brillante, posee propiedades mágicas potentes utilizadas en rituales de clarividencia extrema y encantamientos de evasión. Debido a su delicadeza y estatus protegido, su tráfico se realiza exclusivamente bajo contacto discreto y condiciones especiales de entrega.")
                            .category(criaturas)
                            .rarity(epico)
                            .price_galeon(65)
                            .price_sickle(35)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/Cw76lTr.jpeg")
                            .purchased(false)
                            .build());
            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("CRIA DE KELPIE")
                            .short_description(
                                    "Criatura metamórfica acuática, ideal para protección de terrenos ocultos o rituales de dominación mental.")
                            .long_description(
                                    "El Kelpie es un ser metamórfico de origen acuático, conocido por adoptar formas atractivas para atraer a sus víctimas y arrastrarlas bajo el agua. La cría, aunque menos peligrosa que su versión adulta, ya demuestra habilidades de transformación y una afinidad notable con la magia ilusoria y el control mental básico. Algunos magos oscuros la utilizan como guardiana en lagos encantados, fosos o reservas secretas. Su crianza requiere un estanque encantado y encantamientos de sujeción mental, dado su temperamento impredecible.")
                            .category(criaturas)
                            .rarity(comun)
                            .price_galeon(38)
                            .price_sickle(22)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/FXLYgGx.jpeg")
                            .purchased(false)
                            .build());
            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("HUEVO DE DRAGÓN HUNGARO HORNERO")
                            .short_description(
                                    "Huevo extremadamente raro de una de las especies de dragón más agresivas y poderosas del mundo mágico.")
                            .long_description(
                                    "El Húngaro Hornero es conocido por su fiereza, escamas negras y su aliento ígneo de largo alcance. Su huevo, altamente codiciado y de circulación absolutamente prohibida en la mayoría de jurisdicciones mágicas, representa una reliquia viva del poder dracónico. Si se mantiene en condiciones térmicas controladas y bajo vigilancia de encantamientos de contención, puede eclosionar tras semanas o incluso meses. En el mercado tenebroso, es utilizado tanto para experimentación como para crianza clandestina, con fines bélicos o de intimidación mágica.")
                            .category(criaturas)
                            .rarity(legendario)
                            .price_galeon(120)
                            .price_sickle(47)
                            .price_knut(18)
                            .url_image("https://i.imgur.com/EsAFIZN.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("SANGRE DE INFERI")
                            .short_description(
                                    "Un ingrediente maldito, perfecto para rituales oscuros y pociones de necromancia.")
                            .long_description(
                                    "La Sangre de Inferi proviene de los cadáveres reanimados por magia negra, conocidos como Inferi. Este componente oscuro es utilizado en rituales de necromancia, hechizos de control mental y en la creación de pociones que invocan o controlan a los Inferi. Su naturaleza macabra la convierte en un ingrediente sumamente raro y peligroso, especialmente en manos equivocadas. Se recomienda manejar con extrema cautela, ya que el contacto directo con ella puede tener efectos no deseados en el mago.")
                            .category(ingredientes_para_pociones)
                            .rarity(epico)
                            .price_galeon(30)
                            .price_sickle(45)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/xpVFbGf.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("VENENO DE BASILISCO")
                            .short_description(
                                    "Veneno mortal extraído de los Basiliscos, utilizado en pociones letales y maldiciones severas.")
                            .long_description(
                                    "El Veneno de Basilisco es uno de los compuestos más mortales y peligrosos en el mundo mágico. Extraído de la glándula de un Basilisco, este veneno tiene propiedades extremadamente letales y puede causar la muerte instantánea o petrificación si no se maneja adecuadamente. Se utiliza en la creación de pociones letales, así como en maldiciones que pueden tener efectos devastadores. Solo los magos más oscuros y experimentados se atreven a trabajar con este veneno debido a su poder y su capacidad de causar daño irreversible. Su manejo requiere protección especial y es muy buscado en los mercados clandestinos.")
                            .category(ingredientes_para_pociones)
                            .rarity(epico)
                            .price_galeon(50)
                            .price_sickle(75)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/QtuzI72.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("COLMILLO DE ACROMANTULA")
                            .short_description(
                                    "Diente afilado de una Acromántula, ideal para encantamientos oscuros y venenos especializados.")
                            .long_description(
                                    "El Colmillo de Acromántula es un objeto afilado y resistente, impregnado de veneno natural extremadamente tóxico. Su uso es común en rituales oscuros, elaboración de dagas malditas y como ingrediente central en pociones paralizantes. La Acromántula, criatura arácnida de gran tamaño y agresividad, habita en regiones prohibidas y su captura representa un riesgo letal, lo que eleva considerablemente el valor de sus partes. Este colmillo, además de su potencia mágica, es también un símbolo de poder entre los coleccionistas más sombríos.")
                            .category(ingredientes_para_pociones)
                            .rarity(comun)
                            .price_galeon(22)
                            .price_sickle(30)
                            .price_knut(12)
                            .url_image("https://i.imgur.com/L2LSfUd.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("RAIZ DE MANDRAGORA NEGRA")
                            .short_description(
                                    "Variante oscura de la mandrágora, usada en pociones de control mental y maldiciones silenciadoras.")
                            .long_description(
                                    "La Raíz de Mandrágora Negra es una subespecie poco común de la clásica mandrágora, cultivada en suelos malditos y bajo rituales de magia oscura. A diferencia de la mandrágora convencional, esta variante emite un llanto silencioso que no mata, pero debilita mentalmente a quien lo escucha. Se utiliza en pociones de control, amortiguación de voluntad y hechizos de sumisión prolongada. Su manipulación debe hacerse con guantes encantados, y su conservación requiere entornos oscuros y húmedos.")
                            .category(ingredientes_para_pociones)
                            .rarity(comun)
                            .price_galeon(18)
                            .price_sickle(24)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/FrhvYq6.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("CENIZA DE FENIX OSCURO")
                            .short_description(
                                    "Resto mágico de un renacimiento corrupto, utilizado en invocaciones y transmutaciones oscuras.")
                            .long_description(
                                    "La Ceniza de Fénix Oscuro proviene de fénix que han sido corrompidos mediante maldiciones o rituales prohibidos. A diferencia de las cenizas purificadoras de un fénix tradicional, estas poseen propiedades mágicas inestables, cargadas de energía oscura. Es un componente de alto valor en rituales de resurrección corrupta, pociones de prolongación artificial de la vida y hechizos de transmutación de esencia. Su uso indebido puede tener consecuencias impredecibles, por lo que es reservado para magos experimentados en artes tenebrosas.")
                            .category(ingredientes_para_pociones)
                            .rarity(legendario)
                            .price_galeon(40)
                            .price_sickle(60)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/IX0ov8d.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("RITOS DE SANGRE Y SACRIFICIO")
                            .short_description(
                                    "Compendio ancestral de hechizos oscuros ligados a rituales de sangre y ofrendas mágicas.")
                            .long_description(
                                    "Este grimorio, de encuadernación de cuero encarnado y tintas encantadas, recoge prácticas olvidadas por el Ministerio y prohibidas por la mayoría de los círculos mágicos. Contiene instrucciones precisas para ritos de invocación, transferencia de esencia vital y sacrificios vinculados a maldiciones de larga duración. Solo para magos con la voluntad de cruzar ciertos límites.")
                            .category(libros_prohibidos)
                            .rarity(comun)
                            .price_galeon(18)
                            .price_sickle(6)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/c1JW51N.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("ARTE DE LA NIGROMANCIA")
                            .short_description(
                                    "Tratado prohibido sobre el dominio de la muerte y el control de cuerpos sin alma.")
                            .long_description(
                                    "Considerado uno de los volúmenes más peligrosos del mundo mágico, este libro recopila conocimientos oscuros sobre la animación de cadáveres, manipulación de almas, y comunicación con entidades del más allá. Prohibido por el Ministerio desde hace siglos, se cree que fue escrito por un antiguo mago oscuro cuyo nombre fue eliminado de todos los registros. Poseerlo sin autorización supone una falta grave, aunque muy codiciada por nigromantes modernos.")
                            .category(libros_prohibidos)
                            .rarity(comun)
                            .price_galeon(12)
                            .price_sickle(4)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/gFjEzpQ.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("LIBRO DE LOS HORROCRUXES")
                            .short_description(
                                    "Estudio detallado sobre la división del alma y los métodos para alcanzar la inmortalidad.")
                            .long_description(
                                    "Este tomo altamente peligroso revela los secretos más profundos de la magia oscura: la creación de Horrocruxes. Incluye teorías mágicas, rituales descritos con detalle y anotaciones de antiguos magos tenebrosos que perfeccionaron la técnica. Aunque su posesión está prohibida, es uno de los textos más buscados por aquellos que ansían eludir la muerte sin importar el costo.")
                            .category(libros_prohibidos)
                            .rarity(comun)
                            .price_galeon(28)
                            .price_sickle(9)
                            .price_knut(2)
                            .url_image("https://i.imgur.com/pWPLw0w.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("EL LEGADO DEL MAGISTER TENEBRIS")
                            .short_description(
                                    "Compendio de teorías y hechizos creados por uno de los magos oscuros más enigmáticos de la historia.")
                            .long_description(
                                    "Este libro recoge los pensamientos, rituales y descubrimientos mágicos del misterioso Magister Tenebris, cuya existencia sigue siendo debatida por historiadores mágicos. Incluye conjuros originales, estudios sobre control mental y manipulación de esencia mágica. Considerado una joya oscura por coleccionistas y un peligro por las autoridades mágicas, su lectura exige precaución, ya que algunas páginas parecen reaccionar al lector.")
                            .category(libros_prohibidos)
                            .rarity(comun)
                            .price_galeon(16)
                            .price_sickle(4)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/2lbdD0w.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("EL SUSURRO DE LAS SOMBRAS")
                            .short_description(
                                    "Un tomo prohibido que contiene hechizos de invocación y comunicación con entidades del Velo.")
                            .long_description(
                                    "Este libro explora las fronteras entre el mundo de los vivos y el reino de lo desconocido. Contiene rituales de invocación menor, hechizos para escuchar voces ocultas y pasajes sellados que solo se revelan bajo condiciones específicas. Requiere un alto control mágico para evitar que las entidades invocadas escapen de su contención. Es una fuente valiosa para nigromantes y estudiosos de lo oculto.")
                            .category(libros_prohibidos)
                            .rarity(comun)
                            .price_galeon(17)
                            .price_sickle(3)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/ygjZ58R.jpeg")
                            .purchased(false)
                            .build());
            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("CAPA DE SANGRE")
                            .short_description(
                                    "Vestidura encantada que refuerza hechizos oscuros y oculta la presencia mágica del portador.")
                            .long_description(
                                    "Confeccionada con fibras tratadas en ritos olvidados, esta capa ha sido utilizada por magos tenebrosos durante generaciones. Aumenta la eficacia de maldiciones y conjuros de sangre, y permite al portador pasar desapercibido ante ciertos rastreadores mágicos. Aunque su uso prolongado puede tener efectos secundarios, sigue siendo una pieza codiciada por su poder y rareza.")
                            .category(vestimenta_y_accesorios)
                            .rarity(comun)
                            .price_galeon(13)
                            .price_sickle(9)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/ElzRpZ5.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("TUNICA DE RITOS OSCUROS")
                            .short_description(
                                    "Indumentaria ceremonial empleada en rituales de alta magia tenebrosa.")
                            .long_description(
                                    "Diseñada para canalizar energías oscuras con precisión, esta túnica ha sido utilizada en ceremonias de invocación, pactos de sangre y sellos arcanos. Protege al portador contra interferencias mágicas menores y potencia conjuros relacionados con la dominación, el sacrificio y la transmutación prohibida. No se recomienda para uso prolongado fuera de contextos rituales.")
                            .category(vestimenta_y_accesorios)
                            .rarity(comun)
                            .price_galeon(14)
                            .price_sickle(2)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/dwnpbmi.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("GUANTES DE DRAGÓN")
                            .short_description(
                                    "Resistentes y curtidos, útiles para manipulación de materiales mágicos peligrosos.")
                            .long_description(
                                    "Fabricados con piel auténtica de cola de Galés Verde, estos guantes ofrecen resistencia al fuego, a venenos corrosivos y a sustancias oscuras inestables. Son ideales para pocionistas, herboristas y aquellos que manipulan artefactos imbuídos con magia maldita. Aunque no proporcionan defensa mágica directa, su durabilidad los hace esenciales en todo laboratorio o taller de magia tenebrosa.")
                            .category(vestimenta_y_accesorios)
                            .rarity(comun)
                            .price_galeon(6)
                            .price_sickle(11)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/Oobf5mK.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("ANILLO DE PODER PROHIBIDO")
                            .short_description(
                                    "Canaliza energía mágica oscura a costa de la vitalidad del portador.")
                            .long_description(
                                    "Este anillo, forjado durante una conjunción mágica inestable, está diseñado para potenciar los hechizos ofensivos y maldiciones. Sin embargo, su uso constante agota la energía vital del portador, generando un vínculo progresivo y peligroso con el objeto. A menudo empleado por duelistas oscuros y antiguos seguidores de las artes prohibidas, su fabricación actual está prohibida por el Ministerio de Magia.")
                            .category(vestimenta_y_accesorios)
                            .rarity(comun)
                            .price_galeon(18)
                            .price_sickle(5)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/NKtI4Mu.jpeg")
                            .purchased(false)
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("PAÑUELO DE LUNA NEGRA")
                            .short_description(
                                    "Pañuelo encantado que proporciona invisibilidad parcial bajo la luz de la luna.")
                            .long_description(
                                    "Este pañuelo se utiliza principalmente para facilitar la invisibilidad parcial en la oscuridad, especialmente bajo la luz de la luna llena. Aunque no otorga una invisibilidad total, su efecto es útil para aquellos que deseen pasar desapercibidos por poco tiempo o durante momentos específicos. Es popular entre los observadores nocturnos y aquellos que trabajan en la penumbra de la magia.")
                            .category(vestimenta_y_accesorios)
                            .rarity(comun)
                            .price_galeon(4)
                            .price_sickle(3)
                            .price_knut(0)
                            .url_image("https://i.imgur.com/JKV3oLd.jpeg")
                            .purchased(false)
                            .build());

            System.out.println("🌱 Datos de prueba creados correctamente.");
        }

        if (saleRepository.count() == 0 && saleItemRepository.count() == 0) {
            seedSalesAndItems();
        }
    }

    private void seedSalesAndItems() {
        // Obtener usuarios existentes
        List<UserModel> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found. Please seed users first.");
        }

        // Obtener objetos mágicos existentes
        List<MagicObjectModel> magicObjects = magicObjectRepository.findAll();
        if (magicObjects.isEmpty()) {
            throw new RuntimeException("No magic objects found. Please seed magic objects first.");
        }

        // Crear una venta para el primer usuario
        UserModel user = users.get(0);
        SaleModel sale = SaleModel.builder()
                .user(user)
                .date(new Date(System.currentTimeMillis()))
                .price_galeon(10)
                .price_sickle(5)
                .price_knut(2)
                .build();
        sale = saleRepository.save(sale);

        // Crear elementos de venta asociados a la venta
        for (int i = 0; i < 3; i++) {
            MagicObjectModel magicObject = magicObjects.get(i % magicObjects.size());
            SaleItemModel saleItem = SaleItemModel.builder()
                    .sale(sale)
                    .object(magicObject)
                    .price_galeon(3)
                    .price_sickle(2)
                    .price_knut(1)
                    .build();
            saleItemRepository.save(saleItem);
        }

        // Crear otra venta para el segundo usuario (si existe)
        if (users.size() > 1) {
            UserModel secondUser = users.get(1);
            SaleModel secondSale = SaleModel.builder()
                    .user(secondUser)
                    .date(new Date(System.currentTimeMillis()))
                    .price_galeon(20)
                    .price_sickle(10)
                    .price_knut(5)
                    .build();
            secondSale = saleRepository.save(secondSale);

            // Crear elementos de venta asociados a la segunda venta
            for (int i = 3; i < 6; i++) {
                MagicObjectModel magicObject = magicObjects.get(i % magicObjects.size());
                SaleItemModel saleItem = SaleItemModel.builder()
                        .sale(secondSale)
                        .object(magicObject)
                        .price_galeon(5)
                        .price_sickle(3)
                        .price_knut(2)
                        .build();
                saleItemRepository.save(saleItem);
            }
        }

        System.out.println("🌱 Ventas y elementos de venta creados correctamente.");
    }
}
