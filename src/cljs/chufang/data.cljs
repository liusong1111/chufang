(ns chufang.data
    )

(def data
    [
     ;{:recipe          "处方",
     ; :recipe-filename "chufang",
     ; :source          "出处",
     ; :treat           "功能主治",
     ; :slices
     ;                  [{:slice-name            "饮片1",
     ;                    :slice-filename        "yinpian1",
     ;                    :dosage                "用量",
     ;                    :option-slice          "备用饮片",
     ;                    :option-slice-filename "beiyongyinpian"}
     ;                   {:slice-name            "饮片2",
     ;                    :slice-filename        "yinpian2",
     ;                    :dosage                "用量",
     ;                    :option-slice          "备用饮片",
     ;                    :option-slice-filename "beiyongyinpian"}
     ;                   {:slice-name            "饮片3",
     ;                    :slice-filename        "yinpian3",
     ;                    :dosage                "用量",
     ;                    :option-slice          "备用饮片",
     ;                    :option-slice-filename "beiyongyinpian"}
     ;                   {:slice-name            "饮片4",
     ;                    :slice-filename        "yinpian4",
     ;                    :dosage                "用量",
     ;                    :option-slice          "备用饮片",
     ;                    :option-slice-filename "beiyongyinpian"}
     ;                   {:slice-name            "饮片5",
     ;                    :slice-filename        "yinpian5",
     ;                    :dosage                "用量",
     ;                    :option-slice          "备用饮片",
     ;                    :option-slice-filename "beiyongyinpian"}]}
     {:recipe          "麻黄汤",
      :recipe-filename "mahuangtang",
      :source          "伤寒论",
      :treat           "风寒束表，肺气不宣。主治外感风寒表实证",
      :slices
                       [{:slice-name            "麻黄",
                         :slice-filename        "mahuang",
                         :dosage                "9g",
                         :option-slice          "蜜炙麻黄",
                         :option-slice-filename "mizhimahuang"}
                        {:slice-name            "桂枝",
                         :slice-filename        "guizhi",
                         :dosage                "6g",
                         :option-slice          "桂皮",
                         :option-slice-filename "guipi"}
                        {:slice-name            "_苦杏仁",
                         :slice-filename        "_kuxingren",
                         :dosage                "6g",
                         :option-slice          "苦杏仁",
                         :option-slice-filename "kuxingren"}
                        {:slice-name            "蜜炙甘草",
                         :slice-filename        "mizhigancao",
                         :dosage                "3g",
                         :option-slice          "甘草",
                         :option-slice-filename "gancao"}]}
     {:recipe          "麻杏石甘汤",
      :recipe-filename "maxingshigantang",
      :source          "伤寒论",
      :treat           "辛凉宣泄，清肺平喘。表邪化热，壅遏于肺之喘咳证。",
      :slices
                       [{:slice-name            "麻黄",
                         :slice-filename        "mahuang",
                         :dosage                "9g",
                         :option-slice          "蜜炙麻黄",
                         :option-slice-filename "mizhimahuang"}
                        {:slice-name            "_苦杏仁",
                         :slice-filename        "_kuxingren",
                         :dosage                "9g",
                         :option-slice          "苦杏仁",
                         :option-slice-filename "kuxingren"}
                        {:slice-name            "蜜炙甘草",
                         :slice-filename        "mizhigancao",
                         :dosage                "6g",
                         :option-slice          "甘草",
                         :option-slice-filename "gancao"}
                        {:slice-name            "生石膏",
                         :slice-filename        "shengshigao",
                         :dosage                "24g",
                         :option-slice          "煅石膏",
                         :option-slice-filename "duanshigao"}]}
     {:recipe          "酸枣仁汤",
      :recipe-filename "suanzaorentang",
      :source          "金匮要略",
      :treat           "养血安神，清热除烦。主治肝血不足，虚热扰神证。",
      :slices
                       [{:slice-name            "炒酸枣仁",
                         :slice-filename        "chaosuanzaoren",
                         :dosage                "15g",
                         :option-slice          "生酸枣仁",
                         :option-slice-filename "shengsuanzaoren"}
                        {:slice-name            "茯苓",
                         :slice-filename        "fuling",
                         :dosage                "6g",
                         :option-slice          "土茯苓",
                         :option-slice-filename "tufuling"}
                        {:slice-name            "知母",
                         :slice-filename        "zhimu",
                         :dosage                "6g",
                         :option-slice          "盐知母",
                         :option-slice-filename "yanzhimu"}
                        {:slice-name            "酒川芎",
                         :slice-filename        "jiuchuanxiong",
                         :dosage                "6g",
                         :option-slice          "川芎",
                         :option-slice-filename "chuanxiong"}
                        {:slice-name            "甘草",
                         :slice-filename        "gancao",
                         :dosage                "3g",
                         :option-slice          "蜜炙甘草",
                         :option-slice-filename "mizhigancao"}]}
     {:recipe          "甘麦大枣汤",
      :recipe-filename "ganmaidazaotang",
      :source          "金匮要略",
      :treat           "养血安神，和中缓急。主治心阴受损，肝气失和之脏躁",
      :slices
                       [{:slice-name            "甘草",
                         :slice-filename        "gancao",
                         :dosage                "90g",
                         :option-slice          "蜜炙甘草",
                         :option-slice-filename "mizhigancao"}
                        {:slice-name            "小麦",
                         :slice-filename        "xiaomai",
                         :dosage                "30g",
                         :option-slice          "浮小麦",
                         :option-slice-filename "fuxiaomai"}
                        {:slice-name            "大枣",
                         :slice-filename        "dazao",
                         :dosage                "10枚",
                         :option-slice          "",
                         :option-slice-filename ""}]}
     {:recipe          "四君子汤",
      :recipe-filename "sijunzitang",
      :source          "圣济总录",
      :treat           "益气健脾。主治脾胃气虚证",
      :slices
                       [{:slice-name            "人参",
                         :slice-filename        "rencan",
                         :dosage                "9g",
                         :option-slice          "红参",
                         :option-slice-filename "hongcan"}
                        {:slice-name            "麸炒白术",
                         :slice-filename        "fuchaobaishu",
                         :dosage                "9g",
                         :option-slice          "白术",
                         :option-slice-filename "baishu"}
                        {:slice-name            "茯苓",
                         :slice-filename        "fuling",
                         :dosage                "9g",
                         :option-slice          "土茯苓",
                         :option-slice-filename "tufuling"}
                        {:slice-name            "蜜炙甘草",
                         :slice-filename        "mizhigancao",
                         :dosage                "6g",
                         :option-slice          "甘草",
                         :option-slice-filename "gancao"}]}
     {:recipe          "玉屏风散",
      :recipe-filename "yupingfengsan",
      :source          "医方类聚",
      :treat           "益气固表止汗。主治肺卫气虚证",
      :slices
                       [{:slice-name            "防风",
                         :slice-filename        "fangfeng",
                         :dosage                "30g",
                         :option-slice          "炒防风",
                         :option-slice-filename "chaofangfeng"}
                        {:slice-name            "蜜炙黄芪",
                         :slice-filename        "mizhihuangqi",
                         :dosage                "60g",
                         :option-slice          "黄芪",
                         :option-slice-filename "huangqi"}
                        {:slice-name            "麸炒白术",
                         :slice-filename        "fuchaobaishu",
                         :dosage                "60g",
                         :option-slice          "白术",
                         :option-slice-filename "baishu"}]}
     {:recipe          "四物汤",
      :recipe-filename "siwutang",
      :source          "仙授理伤续断秘方",
      :treat           "补血、和血。主治营血虚滞证。",
      :slices
                       [{:slice-name            "酒当归",
                         :slice-filename        "jiudanggui",
                         :dosage                "9g",
                         :option-slice          "当归",
                         :option-slice-filename "danggui"}
                        {:slice-name            "川芎",
                         :slice-filename        "chuanxiong",
                         :dosage                "6g",
                         :option-slice          "酒川芎",
                         :option-slice-filename "jiuchuanxiong"}
                        {:slice-name            "白芍",
                         :slice-filename        "baishao",
                         :dosage                "9g",
                         :option-slice          "土白芍",
                         :option-slice-filename "tubaishao"}
                        {:slice-name            "酒大黄",
                         :slice-filename        "jiudahuang",
                         :dosage                "12g",
                         :option-slice          "大黄",
                         :option-slice-filename "dahuang"}]}
     {:recipe          "桃核承气汤",
      :recipe-filename "taohechengqitang",
      :source          "伤寒论",
      :treat           "破瘀泄热，兼以攻下。主治下焦蓄血证",
      :slices
                       [{:slice-name            "_桃仁",
                         :slice-filename        "_taoren",
                         :dosage                "12g",
                         :option-slice          "生桃仁",
                         :option-slice-filename "shengtaoren"}
                        {:slice-name            "酒大黄",
                         :slice-filename        "jiudahuang",
                         :dosage                "12g",
                         :option-slice          "大黄",
                         :option-slice-filename "dahuang"}
                        {:slice-name            "桂枝",
                         :slice-filename        "guizhi",
                         :dosage                "6g",
                         :option-slice          "桂皮",
                         :option-slice-filename "guipi"}
                        {:slice-name            "蜜炙甘草",
                         :slice-filename        "mizhigancao",
                         :dosage                "12g",
                         :option-slice          "甘草",
                         :option-slice-filename "gancao"}
                        {:slice-name            "制芒硝",
                         :slice-filename        "zhimangxiao",
                         :dosage                "6g",
                         :option-slice          "芒硝",
                         :option-slice-filename "mangxiao"}]}
     {:recipe          "生化汤",
      :recipe-filename "shenghuatang",
      :source          "傅青主女科",
      :treat           "化瘀生新，温经止痛。 产后血虚受寒，瘀阻胞宫。",
      :slices
                       [{:slice-name            "全当归",
                         :slice-filename        "quandanggui",
                         :dosage                "24g",
                         :option-slice          "当归尾",
                         :option-slice-filename "dangguiwei"}
                        {:slice-name            "川芎",
                         :slice-filename        "chuanxiong",
                         :dosage                "9g",
                         :option-slice          "酒川芎",
                         :option-slice-filename "jiuchuanxiong"}
                        {:slice-name            "_桃仁",
                         :slice-filename        "_taoren",
                         :dosage                "6g",
                         :option-slice          "桃仁",
                         :option-slice-filename "taoren"}
                        {:slice-name            "炮姜",
                         :slice-filename        "paojiang",
                         :dosage                "2g",
                         :option-slice          "干姜",
                         :option-slice-filename "ganjiang"}
                        {:slice-name            "蜜炙甘草",
                         :slice-filename        "mizhigancao",
                         :dosage                "2g",
                         :option-slice          "甘草",
                         :option-slice-filename "gancao"}]}
     {:recipe          "青蒿鳖甲汤",
      :recipe-filename "qinghaobiejiatang",
      :source          "温病条辨",
      :treat           "透热养阴。主治温病后期，邪伏阴分证。",
      :slices
                       [{:slice-name            "青蒿",
                         :slice-filename        "qinghao",
                         :dosage                "6g",
                         :option-slice          "",
                         :option-slice-filename ""}
                        {:slice-name            "醋鳖甲",
                         :slice-filename        "cubiejia",
                         :dosage                "15g",
                         :option-slice          "鳖甲",
                         :option-slice-filename "biejia"}
                        {:slice-name            "生地黄",
                         :slice-filename        "shengdihuang",
                         :dosage                "12g",
                         :option-slice          "熟地黄",
                         :option-slice-filename "shudihuang"}
                        {:slice-name            "知母",
                         :slice-filename        "zhimu",
                         :dosage                "6g",
                         :option-slice          "盐知母",
                         :option-slice-filename "yanzhimu"}
                        {:slice-name            "牡丹皮",
                         :slice-filename        "mudanpi",
                         :dosage                "9g",
                         :option-slice          "",
                         :option-slice-filename ""}]}
     {:recipe          "导赤散",
      :recipe-filename "daochisan",
      :source          "小儿药证直诀",
      :treat           "养阴清心，利水通淋。主治心经有热证；心热移于小肠证。",
      :slices
                       [{:slice-name            "生地黄",
                         :slice-filename        "shengdihuang",
                         :dosage                "6g",
                         :option-slice          "熟地黄",
                         :option-slice-filename "shudihuang"}
                        {:slice-name            "关木通",
                         :slice-filename        "guanmutong",
                         :dosage                "6g",
                         :option-slice          "木通",
                         :option-slice-filename "mutong"}
                        {:slice-name            "甘草",
                         :slice-filename        "gancao",
                         :dosage                "6g",
                         :option-slice          "蜜炙甘草",
                         :option-slice-filename "mizhigancao"}
                        {:slice-name            "竹叶",
                         :slice-filename        "zhuye",
                         :dosage                "6g",
                         :option-slice          "",
                         :option-slice-filename ""}]}
     {:recipe          "黄连解毒汤",
      :recipe-filename "huanglianjiedutang",
      :source          "外台秘要",
      :treat           "泻火解毒。主治三焦火毒热盛证。",
      :slices
                       [{:slice-name            "黄连",
                         :slice-filename        "huanglian",
                         :dosage                "9g",
                         :option-slice          "酒黄连",
                         :option-slice-filename "jiuhuanglian"}
                        {:slice-name            "黄芩",
                         :slice-filename        "huangqin",
                         :dosage                "6g",
                         :option-slice          "酒黄芩",
                         :option-slice-filename "jiuhuangqin"}
                        {:slice-name            "黄柏",
                         :slice-filename        "huangbai",
                         :dosage                "9g",
                         :option-slice          "盐黄柏",
                         :option-slice-filename "yanhuangbai"}
                        {:slice-name            "生栀子",
                         :slice-filename        "shengzhizi",
                         :dosage                "9g",
                         :option-slice          "焦栀子",
                         :option-slice-filename "jiaozhizi"}]}
     {:recipe          "二妙散",
      :recipe-filename "ermiaosan",
      :source          "丹溪心法",
      :treat           "清热燥湿。主治湿热下注证。",
      :slices
                       [{:slice-name            "盐黄柏",
                         :slice-filename        "yanhuangbai",
                         :dosage                "15g",
                         :option-slice          "黄柏",
                         :option-slice-filename "huangbai"}
                        {:slice-name            "麸炒苍术",
                         :slice-filename        "fuchaocangshu",
                         :dosage                "15g",
                         :option-slice          "苍术",
                         :option-slice-filename "cangshu"}]}
     {:recipe          "防己黄芪汤",
      :recipe-filename "fangjihuangqitang",
      :source          "金匮要略",
      :treat           "益气祛风，健脾利水。气虚之风水、风湿证。",
      :slices
                       [{:slice-name            "防己",
                         :slice-filename        "fangji",
                         :dosage                "12g",
                         :option-slice          "",
                         :option-slice-filename ""}
                        {:slice-name            "黄芪",
                         :slice-filename        "huangqi",
                         :dosage                "15g",
                         :option-slice          "蜜炙黄芪",
                         :option-slice-filename "mizhihuangqi"}
                        {:slice-name            "蜜炙甘草",
                         :slice-filename        "mizhigancao",
                         :dosage                "6g",
                         :option-slice          "甘草",
                         :option-slice-filename "gancao"}
                        {:slice-name            "麸炒白术",
                         :slice-filename        "fuchaobaishu",
                         :dosage                "9g",
                         :option-slice          "白术",
                         :option-slice-filename "baishu"}]}
     {:recipe          "玉女煎",
      :recipe-filename "yunvjian",
      :source          "景岳全书",
      :treat           "清胃热、滋肾阴。主治胃热阴虚之牙痛；亦治消渴。",
      :slices
                       [{:slice-name            "生石膏",
                         :slice-filename        "shengshigao",
                         :dosage                "9g",
                         :option-slice          "煅石膏",
                         :option-slice-filename "duanshigao"}
                        {:slice-name            "熟地黄",
                         :slice-filename        "shudihuang",
                         :dosage                "9g",
                         :option-slice          "生地黄",
                         :option-slice-filename "shengdihuang"}
                        {:slice-name            "麦冬",
                         :slice-filename        "maidong",
                         :dosage                "6g",
                         :option-slice          "朱麦冬",
                         :option-slice-filename "zhumaidong"}
                        {:slice-name            "盐知母",
                         :slice-filename        "yanzhimu",
                         :dosage                "5g",
                         :option-slice          "知母",
                         :option-slice-filename "zhimu"}
                        {:slice-name            "牛膝",
                         :slice-filename        "niuxi",
                         :dosage                "5g",
                         :option-slice          "",
                         :option-slice-filename ""}]}
     {:recipe          "真武汤",
      :recipe-filename "zhenwutang",
      :source          "伤寒论",
      :treat           "温阳利水。主治①脾肾阳虚（以肾阳虚为主）之水肿证；②太阳病发汗太过，阳虚水泛。",
      :slices
                       [{:slice-name            "茯苓",
                         :slice-filename        "fuling",
                         :dosage                "9g",
                         :option-slice          "土茯苓",
                         :option-slice-filename "tufuling"}
                        {:slice-name            "土炒白芍",
                         :slice-filename        "tuchaobaishao",
                         :dosage                "9g",
                         :option-slice          "白芍",
                         :option-slice-filename "baishao"}
                        {:slice-name            "麸炒白术",
                         :slice-filename        "fuchaobaishu",
                         :dosage                "9g",
                         :option-slice          "白术",
                         :option-slice-filename "baishu"}
                        {:slice-name            "生姜",
                         :slice-filename        "shengjiang",
                         :dosage                "9g",
                         :option-slice          "干姜",
                         :option-slice-filename "ganjiang"}
                        {:slice-name            "炮附子",
                         :slice-filename        "paofuzi",
                         :dosage                "9g",
                         :option-slice          "黑顺片",
                         :option-slice-filename "heishunpian"}]}
     {:recipe          "四逆汤",
      :recipe-filename "sinitang",
      :source          "伤寒论",
      :treat           "回阳救逆。主治少阴病之阳气衰微，阴寒内盛证。太阳病汗多亡阳者",
      :slices
                       [{:slice-name            "蜜炙甘草",
                         :slice-filename        "mizhigancao",
                         :dosage                "6g",
                         :option-slice          "甘草",
                         :option-slice-filename "gancao"}
                        {:slice-name            "干姜",
                         :slice-filename        "ganjiang",
                         :dosage                "6g",
                         :option-slice          "生姜",
                         :option-slice-filename "shengjiang"}
                        {:slice-name            "生附子",
                         :slice-filename        "shengfuzi",
                         :dosage                "10g",
                         :option-slice          "黑顺片",
                         :option-slice-filename "heishunpian"}]}
     {:recipe          "二陈汤",
      :recipe-filename "erchentang",
      :source          "太平惠民和剂局方",
      :treat           "燥湿化痰，理气和中。 主治湿痰咳嗽证，痰湿眩晕、瘿瘤。",
      :slices
                       [{:slice-name            "清半夏",
                         :slice-filename        "qingbanxia",
                         :dosage                "15g",
                         :option-slice          "生半夏",
                         :option-slice-filename "shengbanxia"}
                        {:slice-name            "橘红",
                         :slice-filename        "juhong",
                         :dosage                "15g",
                         :option-slice          "陈皮",
                         :option-slice-filename "chenpi"}
                        {:slice-name            "茯苓",
                         :slice-filename        "fuling",
                         :dosage                "9g",
                         :option-slice          "土茯苓",
                         :option-slice-filename "tufuling"}
                        {:slice-name            "蜜炙甘草",
                         :slice-filename        "mizhigancao",
                         :dosage                "4.5g",
                         :option-slice          "甘草",
                         :option-slice-filename "gancao"}]}
     {:recipe          "苓桂术甘汤",
      :recipe-filename "lingguishugantang",
      :source          "金匮要略",
      :treat           "温阳化饮，健脾利湿。主治痰饮病。",
      :slices
                       [{:slice-name            "茯苓",
                         :slice-filename        "fuling",
                         :dosage                "12g",
                         :option-slice          "土茯苓",
                         :option-slice-filename "tufuling"}
                        {:slice-name            "桂枝",
                         :slice-filename        "guizhi",
                         :dosage                "9g",
                         :option-slice          "桂皮",
                         :option-slice-filename "guipi"}
                        {:slice-name            "麸炒白术",
                         :slice-filename        "fuchaobaishu",
                         :dosage                "6g",
                         :option-slice          "白术",
                         :option-slice-filename "baishu"}
                        {:slice-name            "蜜炙甘草",
                         :slice-filename        "mizhigancao",
                         :dosage                "6g",
                         :option-slice          "甘草",
                         :option-slice-filename "gancao"}]}]

    )


; 得到一个随机处方列表
(defn sample-data []
    (shuffle data)
    )

