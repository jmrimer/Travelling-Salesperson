package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import org.junit.jupiter.api.BeforeEach;;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseGeneticsTest {
  public int startingParentsCount = 128;
  public int populationCap = 128;
  public int totalGenerations = (int) (Math.pow(2, 8));
  public int maxGeneSequenceLength = 101;
  public double mutationChance = 0;

  Trial trial;
  public City city1 = new City(1, 87.951292, 2.658162);
  public City city2 = new City(2, 33.466597, 66.682943);
  public City city3 = new City(3, 91.778314, 53.807184);
  public City city4 = new City(4, 20.526749, 47.633290);
  public City city5 = new City(5, 9.006012, 81.185339);
  public City city6 = new City(6, 20.032350, 2.761925);
  public City city7 = new City(7, 77.181310, 31.922361);
  public City city8 = new City(8, 41.059603, 32.578509);
  public City city9 = new City(9, 18.692587, 97.015290);
  public City city10 = new City(10, 51.658681, 33.808405);
  private City city11 = new City(11, 44.563128, 47.541734);
  private City city12 = new City(12, 37.806330, 50.599689);
  private City city13 = new City(13, 9.961241, 20.337535);
  private City city14 = new City(14, 28.186895, 70.415357);
  private City city15 = new City(15, 62.129582, 6.183050);
  private City city16 = new City(16, 50.376904, 42.796106);
  private City city17 = new City(17, 71.285134, 43.671987);
  private City city18 = new City(18, 34.156316, 49.113437);
  private City city19 = new City(19, 85.201575, 71.837519);
  private City city20 = new City(20, 27.466659, 1.394696);
  private City city21 = new City(21, 97.985778, 44.746239);
  private City city22 = new City(22, 40.730003, 98.400830);
  private City city23 = new City(23, 73.799860, 61.076693);
  private City city24 = new City(24, 85.076449, 17.029328);
  private City city25 = new City(25, 16.052736, 11.899167);
  private City city26 = new City(26, 20.160527, 67.238380);
  private City city27 = new City(27, 22.730186, 99.725333);
  private City city28 = new City(28, 77.196570, 88.503677);
  private City city29 = new City(29, 18.494217, 31.971191);
  private City city30 = new City(30, 72.743919, 16.071047);
  private City city31 = new City(31, 4.153569, 41.981262);
  private City city32 = new City(32, 79.027680, 95.034639);
  private City city33 = new City(33, 14.145329, 40.690329);
  private City city34 = new City(34, 66.258736, 70.360424);
  private City city35 = new City(35, 22.656941, 52.076785);
  private City city36 = new City(36, 82.680746, 31.058687);
  private City city37 = new City(37, 88.995025, 35.560167);
  private City city38 = new City(38, 87.939085, 36.567278);
  private City city39 = new City(39, 82.845546, 48.393200);
  private City city40 = new City(40, 5.371258, 3.466903);
  private City city41 = new City(41, 80.028687, 51.258889);
  private City city42 = new City(42, 8.908353, 80.703146);
  private City city43 = new City(43, 69.411298, 10.122990);
  private City city44 = new City(44, 10.129093, 91.378521);
  private City city45 = new City(45, 61.546678, 97.531053);
  private City city46 = new City(46, 61.156041, 69.313639);
  private City city47 = new City(47, 39.719840, 46.403394);
  private City city48 = new City(48, 38.999603, 68.407239);
  private City city49 = new City(49, 43.992431, 59.556871);
  private City city50 = new City(50, 26.963103, 73.021638);
  private City city51 = new City(51, 28.879666, 27.948851);
  private City city52 = new City(52, 58.751183, 87.429426);
  private City city53 = new City(53, 85.290078, 60.875271);
  private City city54 = new City(54, 40.879543, 32.523576);
  private City city55 = new City(55, 67.326884, 81.203650);
  private City city56 = new City(56, 19.064913, 27.845088);
  private City city57 = new City(57, 14.648885, 88.753929);
  private City city58 = new City(58, 4.153569, 87.118137);
  private City city59 = new City(59, 10.895108, 44.978179);
  private City city60 = new City(60, 23.258156, 5.346843);
  private City city61 = new City(61, 68.926054, 82.073428);
  private City city62 = new City(62, 11.713004, 65.706351);
  private City city63 = new City(63, 83.404035, 89.590136);
  private City city64 = new City(64, 11.471908, 44.187750);
  private City city65 = new City(65, 41.422773, 81.743828);
  private City city66 = new City(66, 91.595202, 40.324107);
  private City city67 = new City(67, 31.730094, 98.501541);
  private City city68 = new City(68, 56.382946, 11.935789);
  private City city69 = new City(69, 43.232521, 43.571276);
  private City city70 = new City(70, 56.904813, 42.152165);
  private City city71 = new City(71, 93.386639, 12.457656);
  private City city72 = new City(72, 71.395001, 16.754662);
  private City city73 = new City(73, 77.065340, 13.657033);
  private City city74 = new City(74, 70.278024, 40.021973);
  private City city75 = new City(75, 76.604511, 36.146123);
  private City city76 = new City(76, 31.351665, 67.159032);
  private City city77 = new City(77, 23.563341, 66.295358);
  private City city78 = new City(78, 20.822779, 81.447798);
  private City city79 = new City(79, 52.903836, 7.309183);
  private City city80 = new City(80, 5.746635, 94.280831);
  private City city81 = new City(81, 40.147099, 4.345836);
  private City city82 = new City(82, 13.583789, 96.127201);
  private City city83 = new City(83, 62.181463, 28.403577);
  private City city84 = new City(84, 4.409925, 36.637471);
  private City city85 = new City(85, 72.331919, 22.144230);
  private City city86 = new City(86, 71.483505, 61.964782);
  private City city87 = new City(87, 30.283517, 60.740989);
  private City city88 = new City(88, 35.721915, 87.408063);
  private City city89 = new City(89, 77.556688, 30.884732);
  private City city90 = new City(90, 49.781793, 33.585620);
  private City city91 = new City(91, 99.078341, 81.115146);
  private City city92 = new City(92, 77.309488, 4.168828);
  private City city93 = new City(93, 61.522263, 46.504105);
  private City city94 = new City(94, 63.026826, 33.359783);
  private City city95 = new City(95, 69.045076, 0.952177);
  private City city96 = new City(96, 59.254738, 81.203650);
  private City city97 = new City(97, 27.005829, 40.083010);
  private City city98 = new City(98, 24.509415, 4.898221);
  private City city99 = new City(99, 54.347362, 47.959838);
  private City city100 = new City(100, 59.797967, 84.215827);

  private City city222_1 = new City(1, 65.556810, 28.479873);
  private City city222_2 = new City(2, 41.242714, 88.866848);
  private City city222_3 = new City(3, 62.334056, 89.416181);
  private City city222_4 = new City(4, 11.673330, 41.239662);
  private City city222_5 = new City(5, 95.135350, 28.467666);
  private City city222_6 = new City(6, 32.474746, 90.038759);
  private City city222_7 = new City(7, 29.175695, 38.105411);
  private City city222_8 = new City(8, 7.904294, 61.720634);
  private City city222_9 = new City(9, 58.302561, 4.644917);
  private City city222_10 = new City(10, 82.604450, 66.423536);
  private City city222_11 = new City(11, 85.204627, 19.003876);
  private City city222_12 = new City(12, 88.750877, 19.739372);
  private City city222_13 = new City(13, 70.619831, 97.799615);
  private City city222_14 = new City(14, 13.672292, 57.679983);
  private City city222_15 = new City(15, 67.070528, 42.936491);
  private City city222_16 = new City(16, 28.647725, 97.906430);
  private City city222_17 = new City(17, 2.826014, 17.801447);
  private City city222_18 = new City(18, 69.414350, 50.398267);
  private City city222_19 = new City(19, 31.662954, 68.156987);
  private City city222_20 = new City(20, 9.085360, 30.643635);
  private City city222_21 = new City(21, 14.069033, 65.910825);
  private City city222_22 = new City(22, 14.081240, 78.502762);
  private City city222_23 = new City(23, 45.542772, 3.854488);
  private City city222_24 = new City(24, 23.667104, 35.843989);
  private City city222_25 = new City(25, 97.570727, 19.507431);
  private City city222_26 = new City(26, 43.778802, 86.129337);
  private City city222_27 = new City(27, 86.910611, 76.180303);
  private City city222_28 = new City(28, 95.699942, 8.990753);
  private City city222_29 = new City(29, 38.853114, 79.387799);
  private City city222_30 = new City(30, 10.278634, 53.025910);
  private City city222_31 = new City(31, 60.042116, 56.752220);
  private City city222_32 = new City(32, 8.258309, 79.818110);
  private City city222_33 = new City(33, 86.458937, 79.497665);
  private City city222_34 = new City(34, 57.371746, 82.433546);
  private City city222_35 = new City(35, 22.266305, 53.672903);
  private City city222_36 = new City(36, 46.794031, 31.421857);
  private City city222_37 = new City(37, 35.102390, 59.227271);
  private City city222_38 = new City(38, 69.664602, 51.487777);
  private City city222_39 = new City(39, 23.398541, 65.056307);
  private City city222_40 = new City(40, 54.170354, 81.945250);
  private City city222_41 = new City(41, 21.436201, 98.162786);
  private City city222_42 = new City(42, 33.936583, 24.100467);
  private City city222_43 = new City(43, 57.701346, 95.028535);
  private City city222_44 = new City(44, 98.278756, 28.675192);
  private City city222_45 = new City(45, 32.200079, 13.345744);
  private City city222_46 = new City(46, 12.952055, 26.358837);
  private City city222_47 = new City(47, 28.333384, 32.242805);
  private City city222_48 = new City(48, 52.085940, 54.710532);
  private City city222_49 = new City(49, 24.961089, 19.058809);
  private City city222_50 = new City(50, 31.794183, 84.408094);
  private City city222_51 = new City(51, 24.304941, 90.914640);
  private City city222_52 = new City(52, 86.248360, 98.092593);
  private City city222_53 = new City(53, 61.290323, 49.070711);
  private City city222_54 = new City(54, 76.689962, 24.839015);
  private City city222_55 = new City(55, 9.939879, 14.313181);
  private City city222_56 = new City(56, 74.144719, 83.443709);
  private City city222_57 = new City(57, 52.488784, 41.413617);
  private City city222_58 = new City(58, 6.329539, 37.592700);
  private City city222_59 = new City(59, 26.807459, 40.803247);
  private City city222_60 = new City(60, 9.494308, 16.113773);
  private City city222_61 = new City(61, 17.770928, 85.644093);
  private City city222_62 = new City(62, 75.728629, 1.342814);
  private City city222_63 = new City(63, 8.200323, 32.325205);
  private City city222_64 = new City(64, 36.420789, 38.822596);
  private City city222_65 = new City(65, 54.988250, 32.605976);
  private City city222_66 = new City(66, 72.502823, 71.385846);
  private City city222_67 = new City(67, 6.067080, 65.446944);
  private City city222_68 = new City(68, 92.019410, 50.788903);
  private City city222_69 = new City(69, 23.804437, 66.713462);
  private City city222_70 = new City(70, 65.746025, 30.024110);
  private City city222_71 = new City(71, 30.478835, 84.859767);
  private City city222_72 = new City(72, 61.442915, 89.385662);
  private City city222_73 = new City(73, 41.431928, 40.690329);
  private City city222_74 = new City(74, 67.332987, 99.481185);
  private City city222_75 = new City(75, 1.574755, 19.391461);
  private City city222_76 = new City(76, 45.493942, 74.266793);
  private City city222_77 = new City(77, 90.810877, 72.856838);
  private City city222_78 = new City(78, 5.746635, 17.255165);
  private City city222_79 = new City(79, 14.023255, 60.539567);
  private City city222_80 = new City(80, 40.681173, 14.014100);
  private City city222_81 = new City(81, 41.306803, 6.878872);
  private City city222_82 = new City(82, 12.421033, 74.053163);
  private City city222_83 = new City(83, 13.959166, 3.469955);
  private City city222_84 = new City(84, 31.806391, 75.289163);
  private City city222_85 = new City(85, 81.954405, 34.055605);
  private City city222_86 = new City(86, 20.551164, 14.139225);
  private City city222_87 = new City(87, 88.460952, 50.535600);
  private City city222_88 = new City(88, 97.051912, 14.444411);
  private City city222_89 = new City(89, 67.104099, 78.054140);
  private City city222_90 = new City(90, 76.436659, 98.260445);
  private City city222_91 = new City(91, 67.479476, 77.303385);
  private City city222_92 = new City(92, 25.534837, 71.706290);
  private City city222_93 = new City(93, 74.672689, 42.204047);
  private City city222_94 = new City(94, 63.298441, 29.004791);
  private City city222_95 = new City(95, 38.306833, 61.052278);
  private City city222_96 = new City(96, 47.413556, 27.768792);
  private City city222_97 = new City(97, 76.818140, 19.251076);
  private City city222_98 = new City(98, 37.617115, 38.724937);
  private City city222_99 = new City(99, 72.524186, 71.803949);
  private City city222_100 = new City(100, 76.445814, 22.974334);
  private City city222_101 = new City(101, 23.154393, 63.621937);
  private City city222_102 = new City(102, 77.950377, 39.906003);
  private City city222_103 = new City(103, 65.453047, 25.827815);
  private City city222_104 = new City(104, 65.071566, 38.923307);
  private City city222_105 = new City(105, 36.454360, 26.181829);
  private City city222_106 = new City(106, 46.266060, 38.059633);
  private City city222_107 = new City(107, 88.116092, 1.669362);
  private City city222_108 = new City(108, 72.005371, 42.643513);
  private City city222_109 = new City(109, 20.850246, 51.524400);
  private City city222_110 = new City(110, 61.781671, 7.123020);
  private City city222_111 = new City(111, 51.680044, 18.649861);
  private City city222_112 = new City(112, 42.283395, 93.203528);
  private City city222_113 = new City(113, 82.534257, 37.711722);
  private City city222_114 = new City(114, 87.270730, 27.344584);
  private City city222_115 = new City(115, 84.414197, 77.895444);
  private City city222_116 = new City(116, 99.874874, 10.867641);
  private City city222_117 = new City(117, 62.965789, 58.558916);
  private City city222_118 = new City(118, 27.900021, 85.668508);
  private City city222_119 = new City(119, 21.143223, 65.840632);
  private City city222_120 = new City(120, 52.739036, 8.740501);
  private City city222_121 = new City(121, 84.575945, 48.377941);
  private City city222_122 = new City(122, 42.570269, 38.633381);
  private City city222_123 = new City(123, 5.206458, 20.252083);
  private City city222_124 = new City(124, 10.412915, 86.904508);
  private City city222_125 = new City(125, 8.960234, 49.275185);
  private City city222_126 = new City(126, 67.726676, 37.769707);
  private City city222_127 = new City(127, 2.896207, 58.754234);
  private City city222_128 = new City(128, 14.084292, 89.043855);
  private City city222_129 = new City(129, 7.605213, 20.877712);
  private City city222_130 = new City(130, 53.691214, 17.468795);
  private City city222_131 = new City(131, 30.420850, 69.814142);
  private City city222_132 = new City(132, 79.177221, 21.607105);
  private City city222_133 = new City(133, 45.060579, 12.640767);
  private City city222_134 = new City(134, 13.495285, 45.393231);
  private City city222_135 = new City(135, 93.359172, 66.292306);
  private City city222_136 = new City(136, 50.410474, 35.447249);
  private City city222_137 = new City(137, 74.666585, 98.577837);
  private City city222_138 = new City(138, 84.218879, 35.355693);
  private City city222_139 = new City(139, 38.605914, 1.434370);
  private City city222_140 = new City(140, 69.405194, 69.942320);
  private City city222_141 = new City(141, 46.742149, 74.544511);
  private City city222_142 = new City(142, 0.778222, 22.721030);
  private City city222_143 = new City(143, 18.839076, 47.398297);
  private City city222_144 = new City(144, 54.744102, 59.654530);
  private City city222_145 = new City(145, 29.300821, 17.996765);
  private City city222_146 = new City(146, 82.314524, 2.285836);
  private City city222_147 = new City(147, 69.454024, 36.259041);
  private City city222_148 = new City(148, 94.549394, 3.463851);
  private City city222_149 = new City(149, 41.245766, 10.272530);
  private City city222_150 = new City(150, 30.957976, 28.675192);
  private City city222_151 = new City(151, 98.034608, 63.542589);
  private City city222_152 = new City(152, 51.564074, 96.212653);
  private City city222_153 = new City(153, 98.068178, 21.332438);
  private City city222_154 = new City(154, 43.205054, 79.229102);
  private City city222_155 = new City(155, 86.284982, 54.225288);
  private City city222_156 = new City(156, 80.739769, 22.837001);
  private City city222_157 = new City(157, 70.061342, 57.042146);
  private City city222_158 = new City(158, 33.729057, 79.393902);
  private City city222_159 = new City(159, 4.510636, 91.250343);
  private City city222_160 = new City(160, 59.297464, 15.021210);
  private City city222_161 = new City(161, 53.019807, 68.935209);
  private City city222_162 = new City(162, 71.700186, 90.618610);
  private City city222_163 = new City(163, 38.550981, 6.549272);
  private City city222_164 = new City(164, 29.313028, 70.326853);
  private City city222_165 = new City(165, 51.442000, 26.474807);
  private City city222_166 = new City(166, 29.618213, 60.737938);
  private City city222_167 = new City(167, 29.233680, 34.568316);
  private City city222_168 = new City(168, 52.809229, 33.835871);
  private City city222_169 = new City(169, 74.233222, 51.823481);
  private City city222_170 = new City(170, 44.660787, 36.872463);
  private City city222_171 = new City(171, 24.341563, 28.254036);
  private City city222_172 = new City(172, 79.302347, 42.295602);
  private City city222_173 = new City(173, 42.899869, 17.108676);
  private City city222_174 = new City(174, 92.474136, 40.809351);
  private City city222_175 = new City(175, 43.043306, 37.595752);
  private City city222_176 = new City(176, 25.913266, 30.756554);
  private City city222_177 = new City(177, 12.277596, 4.181036);
  private City city222_178 = new City(178, 73.531297, 6.891079);
  private City city222_179 = new City(179, 52.873318, 50.633259);
  private City city222_180 = new City(180, 69.927061, 1.709037);
  private City city222_181 = new City(181, 3.701895, 26.895962);
  private City city222_182 = new City(182, 11.951048, 82.424390);
  private City city222_183 = new City(183, 59.160131, 96.969512);
  private City city222_184 = new City(184, 53.874325, 56.740013);
  private City city222_185 = new City(185, 50.743126, 15.857418);
  private City city222_186 = new City(186, 66.823328, 90.548418);
  private City city222_187 = new City(187, 6.997894, 13.782159);
  private City city222_188 = new City(188, 3.106784, 90.643025);
  private City city222_189 = new City(189, 57.380902, 65.706351);
  private City city222_190 = new City(190, 56.041139, 55.632191);
  private City city222_191 = new City(191, 95.693838, 74.358348);
  private City city222_192 = new City(192, 55.891598, 48.091067);
  private City city222_193 = new City(193, 68.721580, 44.862209);
  private City city222_194 = new City(194, 92.620624, 91.180151);
  private City city222_195 = new City(195, 5.703909, 79.082614);
  private City city222_196 = new City(196, 5.960265, 42.759484);
  private City city222_197 = new City(197, 54.210028, 67.815180);
  private City city222_198 = new City(198, 33.542894, 50.202948);
  private City city222_199 = new City(199, 38.123722, 8.972442);
  private City city222_200 = new City(200, 57.618946, 38.972137);
  private City city222_201 = new City(201, 3.311258, 29.865413);
  private City city222_202 = new City(202, 41.071810, 38.120670);
  private City city222_203 = new City(203, 66.606647, 41.697439);
  private City city222_204 = new City(204, 93.295083, 95.837275);
  private City city222_205 = new City(205, 33.735160, 4.416028);
  private City city222_206 = new City(206, 56.584368, 88.143559);
  private City city222_207 = new City(207, 1.831111, 30.069887);
  private City city222_208 = new City(208, 53.645436, 73.891415);
  private City city222_209 = new City(209, 12.121952, 2.713095);
  private City city222_210 = new City(210, 86.901456, 99.166845);
  private City city222_211 = new City(211, 68.459120, 43.089084);
  private City city222_212 = new City(212, 30.115665, 4.239021);
  private City city222_213 = new City(213, 87.261574, 96.127201);
  private City city222_214 = new City(214, 3.106784, 36.170537);
  private City city222_215 = new City(215, 94.570757, 69.435713);
  private City city222_216 = new City(216, 70.021668, 20.203253);
  private City city222_217 = new City(217, 35.770745, 0.903348);
  private City city222_218 = new City(218, 51.896725, 93.096713);
  private City city222_219 = new City(219, 13.638722, 83.504746);
  private City city222_220 = new City(220, 94.125187, 51.451155);
  private City city222_221 = new City(221, 20.334483, 25.458541);
  private City city222_222 = new City(222, 79.381695, 18.243965);

  public Map map5 = new Map(List.of(
    city1,
    city2,
    city3,
    city4,
    city5
  ));

  public Map map10 = new Map(List.of(
    city1,
    city2,
    city3,
    city4,
    city5,
    city6,
    city7,
    city8,
    city9,
    city10
  ));

  public Map map100 = new Map(List.of(
    city1,
    city2,
    city3,
    city4,
    city5,
    city6,
    city7,
    city8,
    city9,
    city10,
    city11,
    city12,
    city13,
    city14,
    city15,
    city16,
    city17,
    city18,
    city19,
    city20,
    city21,
    city22,
    city23,
    city24,
    city25,
    city26,
    city27,
    city28,
    city29,
    city30,
    city31,
    city32,
    city33,
    city34,
    city35,
    city36,
    city37,
    city38,
    city39,
    city40,
    city41,
    city42,
    city43,
    city44,
    city45,
    city46,
    city47,
    city48,
    city49,
    city50,
    city51,
    city52,
    city53,
    city54,
    city55,
    city56,
    city57,
    city58,
    city59,
    city60,
    city61,
    city62,
    city63,
    city64,
    city65,
    city66,
    city67,
    city68,
    city69,
    city70,
    city71,
    city72,
    city73,
    city74,
    city75,
    city76,
    city77,
    city78,
    city79,
    city80,
    city81,
    city82,
    city83,
    city84,
    city85,
    city86,
    city87,
    city88,
    city89,
    city90,
    city91,
    city92,
    city93,
    city94,
    city95,
    city96,
    city97,
    city98,
    city99,
    city100
  ));

  public Map map222 = new Map(
    List.of(
      city222_1,
      city222_2,
      city222_3,
      city222_4,
      city222_5,
      city222_6,
      city222_7,
      city222_8,
      city222_9,
      city222_10,
      city222_11,
      city222_12,
      city222_13,
      city222_14,
      city222_15,
      city222_16,
      city222_17,
      city222_18,
      city222_19,
      city222_20,
      city222_21,
      city222_22,
      city222_23,
      city222_24,
      city222_25,
      city222_26,
      city222_27,
      city222_28,
      city222_29,
      city222_30,
      city222_31,
      city222_32,
      city222_33,
      city222_34,
      city222_35,
      city222_36,
      city222_37,
      city222_38,
      city222_39,
      city222_40,
      city222_41,
      city222_42,
      city222_43,
      city222_44,
      city222_45,
      city222_46,
      city222_47,
      city222_48,
      city222_49,
      city222_50,
      city222_51,
      city222_52,
      city222_53,
      city222_54,
      city222_55,
      city222_56,
      city222_57,
      city222_58,
      city222_59,
      city222_60,
      city222_61,
      city222_62,
      city222_63,
      city222_64,
      city222_65,
      city222_66,
      city222_67,
      city222_68,
      city222_69,
      city222_70,
      city222_71,
      city222_72,
      city222_73,
      city222_74,
      city222_75,
      city222_76,
      city222_77,
      city222_78,
      city222_79,
      city222_80,
      city222_81,
      city222_82,
      city222_83,
      city222_84,
      city222_85,
      city222_86,
      city222_87,
      city222_88,
      city222_89,
      city222_90,
      city222_91,
      city222_92,
      city222_93,
      city222_94,
      city222_95,
      city222_96,
      city222_97,
      city222_98,
      city222_99,
      city222_100,
      city222_101,
      city222_102,
      city222_103,
      city222_104,
      city222_105,
      city222_106,
      city222_107,
      city222_108,
      city222_109,
      city222_110,
      city222_111,
      city222_112,
      city222_113,
      city222_114,
      city222_115,
      city222_116,
      city222_117,
      city222_118,
      city222_119,
      city222_120,
      city222_121,
      city222_122,
      city222_123,
      city222_124,
      city222_125,
      city222_126,
      city222_127,
      city222_128,
      city222_129,
      city222_130,
      city222_131,
      city222_132,
      city222_133,
      city222_134,
      city222_135,
      city222_136,
      city222_137,
      city222_138,
      city222_139,
      city222_140,
      city222_141,
      city222_142,
      city222_143,
      city222_144,
      city222_145,
      city222_146,
      city222_147,
      city222_148,
      city222_149,
      city222_150,
      city222_151,
      city222_152,
      city222_153,
      city222_154,
      city222_155,
      city222_156,
      city222_157,
      city222_158,
      city222_159,
      city222_160,
      city222_161,
      city222_162,
      city222_163,
      city222_164,
      city222_165,
      city222_166,
      city222_167,
      city222_168,
      city222_169,
      city222_170,
      city222_171,
      city222_172,
      city222_173,
      city222_174,
      city222_175,
      city222_176,
      city222_177,
      city222_178,
      city222_179,
      city222_180,
      city222_181,
      city222_182,
      city222_183,
      city222_184,
      city222_185,
      city222_186,
      city222_187,
      city222_188,
      city222_189,
      city222_190,
      city222_191,
      city222_192,
      city222_193,
      city222_194,
      city222_195,
      city222_196,
      city222_197,
      city222_198,
      city222_199,
      city222_200,
      city222_201,
      city222_202,
      city222_203,
      city222_204,
      city222_205,
      city222_206,
      city222_207,
      city222_208,
      city222_209,
      city222_210,
      city222_211,
      city222_212,
      city222_213,
      city222_214,
      city222_215,
      city222_216,
      city222_217,
      city222_218,
      city222_219,
      city222_220,
      city222_221,
      city222_222
    )
  );

  @BeforeEach
  public void setup() {
    trial = new Trial();
  }

  List<LivingTour> finalPopulation;

  public void logResults(Trial trial, long duration, long timestamp) {
    finalPopulation = trial.getGenerations().get(trial.getGenerations().size() - 1).getPopulation();
    testLength();

    LivingTour child = trial.bestChild();
    System.out.println("best child weight: " + child.getWeight() + " & size: " + new HashSet<>(child.getCycle()).size());
    try {
      FileWriter csvWriter = new FileWriter("./testlogs/" + "Consolidated" + this.getClass().getSimpleName() + timestamp + ".csv", true);
      String row = timestamp + "," + child.getWeight() + "," + duration;
      csvWriter.append(row).append("\n");
      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testLength() {
    for (LivingTour livingTour : finalPopulation) {
      assertEquals(100, new HashSet<>(livingTour.getCycle()).size());
    }
  }
}
