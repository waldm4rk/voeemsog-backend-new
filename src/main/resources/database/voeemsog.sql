--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: room_translations; Type: TABLE; Schema: public; Owner: sitemployee
--

CREATE TABLE public.room_translations (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    lang character varying(255) DEFAULT 'ru-RU'::character varying NOT NULL,
    name character varying(255) NOT NULL,
    description bpchar NOT NULL,
    room_id uuid NOT NULL
);


ALTER TABLE public.room_translations OWNER TO sitemployee;

--
-- Name: rooms; Type: TABLE; Schema: public; Owner: sitemployee
--

CREATE TABLE public.rooms (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    code character varying(255) NOT NULL,
    floor smallint DEFAULT 1 NOT NULL,
    danger_type character varying(255),
    size character varying(255) DEFAULT '2x2'::character varying,
    color character varying(255) DEFAULT 'RED'::character varying,
    light_level smallint DEFAULT 1
);


ALTER TABLE public.rooms OWNER TO sitemployee;

--
-- Data for Name: room_translations; Type: TABLE DATA; Schema: public; Owner: sitemployee
--

COPY public.room_translations (id, lang, name, description, room_id) FROM stdin;
f5ad36a8-e237-4a19-a99b-c5e6b3066395	ru_RU	Дальше бога нет	**Описание помещения:**  \nОба входа в AK-610-1 оформлены как **тяжёлые двустворчатые двери**, на которых сотрудники SIT закрепили предупреждающие знаки:\n\n> **[[ПЛАКАТ]]**  \n> 🚫 **ВНИМАНИЕ**: Помещение AK-610-1 классифицировано как смертельно опасное.  \n> Вход возможен только по протоколу AA-3.  \n> Повторный вход без разрешения считается самоубийством.  \n> **Обратного пути нет.**\n\n**Механика воздействия:**  \nСразу после входа дверь за субъектом **автоматически блокируется**. Ни физическое воздействие, ни взлом, ни специализированные устройства не позволяют открыть её повторно. Помещение не оказывает никакого эффекта, если субъект остаётся на месте — не двигается, не делает шагов.\n\nОднако **каждое перемещение** активирует аномальные свойства зала. С каждым шагом субъект испытывает **нарастающие физические травмы**, возникающие без видимого источника. Интенсивность и характер увечий зависят от **ширины шага**:\n\n- **Узкие шаги** провоцируют множество **лёгких, но мучительных травм** — ссадины, переломы пальцев, вывихи суставов, разрывы мышц.\n    \n- **Широкие шаги** наносят **меньшее число, но более серьёзных повреждений** — внутренние разрывы, сломанные рёбра, кровотечения.\n    \n\nУстановлено, что вне зависимости от выбранной стратегии, к концу зала субъект оказывается на грани гибели: отказы дыхательной системы, критические повреждения внутренних органов, потеря сознания.\n\n**Интерьер и атмосфера:**  \nВдоль стен зала расставлены **религиозные предметы разных конфессий**, словно оставленные в попытке обрести прощение:\n\n- **Обугленные свечи**, покрытые копотью, стоящие в рассыпанной золе.\n    \n- **Иконы**, исписанные молитвами, но залитые запёкшейся кровью — надписи невозможно прочитать.\n    \n- **Католические распятия**, повернутые лицом к стене, некоторые — с вырванными гвоздями.\n    \n- **Библия и Коран** с вырванными страницами, оставленные в углах.\n    \n\nКажется, всё помещение пропитано **ощущением чужого раскаяния**. Некоторые субъекты утверждают, что чувствовали, будто кто-то смотрит на них сверху, из темноты потолка, или слышали дыхание за спиной при каждом шаге.\n\n**Завершение прохождения:**  \nНа противоположной стороне находится **вторая дверь**, визуально идентичная первой. Она **открыта** — и это единственный возможный путь выхода. \n\n**Примечания:**\n\n- В помещении не зафиксировано ни одного существа, воздействие — исключительно пространственного характера.\n- За всю историю существования SIT ни один субъект не дошел до конца коридора.	5d09d9c9-ba45-44ea-a3b9-7a8be0c83484
98f320dd-78ca-4598-95c3-2f05f176c20c	en_US	No God Beyond	Both entrances to AK-610-1 are marked by **heavy double doors**, upon which SIT personnel have affixed warning signs:\n\n> **[[POSTER]]**  \n> 🚫 **WARNING**: Room AK-610-1 is classified as lethally dangerous.  \n> Entry permitted only under Protocol AA-3.  \n> Re-entry without authorization is considered suicide.  \n> **There is no way back.**\n\n**Effect Mechanics:**  \nImmediately upon entry, the door behind the subject **automatically locks**. Neither physical force, nor tools, nor specialized equipment can reopen it. The room remains inert if the subject **does not move** — taking no steps and remaining still.\n\nHowever, **each movement** triggers the room’s anomalous effects. With every step, the subject experiences **increasing physical trauma** with no visible cause. The intensity and type of injury depend on the **width of the step**:\n\n- **Short steps** induce numerous **minor yet agonizing injuries** — abrasions, fractured fingers, dislocated joints, torn muscles.\n    \n- **Wide steps** result in **fewer but more severe wounds** — internal ruptures, broken ribs, heavy bleeding.\n    \n\nRegardless of the chosen strategy, by the end of the hallway, the subject is inevitably near death: respiratory failure, critical internal damage, and loss of consciousness.\n\n**Interior and Atmosphere:**  \nThe walls of the room are lined with **religious artifacts from various faiths**, seemingly left behind in desperate pursuit of absolution:\n\n- **Charred candles**, coated in soot and scattered ashes.\n    \n- **Icons**, scrawled with prayers now obscured by **dried blood** — inscriptions rendered unreadable.\n    \n- **Catholic crucifixes**, turned to face the wall, some with nails violently removed.\n    \n- **Bibles and Qurans** with torn-out pages, abandoned in corners.\n    \n\nThe entire space seems infused with a sense of **someone else’s remorse**. Some subjects have reported feeling as if **something watches them from above**, hidden in the ceiling’s shadows, or hearing **breathing behind them** with every step.\n\n**Completion:**  \nAt the far end of the hall stands a **second door**, visually identical to the first. It is **unlocked** — the only possible exit.\n\n**Notes:**\n\n- No entities have been observed inside; the room's effects are purely spatial in nature.\n    \n- Throughout SIT’s operational history, **no subject has ever reached the far door**.	5d09d9c9-ba45-44ea-a3b9-7a8be0c83484
bcf4aebb-61e9-453b-b9cd-3ad6f6d666a9	ru_RU	Лестница вверх 1	**Описание помещения:**  \nBP-429-1 переходит в короткую, но круто восходящую лестницу. Потолок низкий — около 2 метров — и нависает над субъектом, вызывая лёгкое клаустрофобное ощущение, особенно при приближении к лестничной части. Стены окрашены в выцветший оливково-серый цвет и местами покрыты пятнами, похожими на водяные разводы. На всём протяжении коридора царит сухая тишина, нарушаемая лишь эхом собственных шагов.\n\nПо обеим стенам размещены **семейные фотографии в деревянных рамах**. Всего — 18 штук, по 9 с каждой стороны. Изображения различны: пожилые пары, родители с детьми, групповые портреты на фоне неузнаваемых интерьеров. Лица улыбаются, но улыбки кажутся напряжёнными, слишком широкими. Все фотографии сняты в однотонной жёлтой гамме, как будто через старый светофильтр или потускневшую фотоплёнку. Неизвестны личности на фото — ни одна попытка идентификации не увенчалась успехом. Некоторые субъекты утверждали, что лица на снимках им знакомы, но не могли назвать ни имён, ни деталей.\n\nПо данным SIT, фотографии обладают **аномальной стойкостью**: их невозможно разбить или сжечь. При попытке сфотографировать или скопировать изображения они искажаются — лица на снимках размываются, а фон исчезает.\n\n**Переход в лестницу:**  \nПосле прохождения коридора субъект сталкивается с переходом на **лестницу из старого дерева**, врезанную в корпус комнаты. Лестница состоит из 12 ступеней и резко уходит вверх под углом около 50°. Поручней нет. Деревянные доски скрипят, но не прогибаются и не ломаются, даже при значительном весе. При шаге на третью ступень слышен еле уловимый **щелчок**, источник которого не удалось установить — ни одна аппаратура не фиксирует посторонних звуков в этот момент.\n\nСлева, над шестой ступенью, висит металлическая табличка с надписью:  \n**«Ты уже был здесь?»**  \nНадпись выполнена вручную, фломастером, и, по словам наблюдателей, ранее отсутствовала.\n\n**Верхняя дверь:**  \nЛестница ведёт к массивной двери, на которой закреплён **предупредительный плакат**, установленный SIT:\n\n> ### [[ФОТОГРАФИЯ ПЛАКАТА]]\n\n**Примечания:**\n\n- BP-429-1 — первое помещение, зафиксированное SIT, ведущее на **второй этаж** Воеемсога.\n    \n- Фотографии изъяты и принадлежат организации SIT.	161cfb09-a5d8-416f-94f1-f51bf128e03b
e53c9e82-979c-401d-a7b7-6d7ee097aed8	en_US	Stairway up 1	BP-429-1 transitions into a short but steep upward staircase. The ceiling is low — about 2 meters — and presses down on the subject, evoking a mild sense of claustrophobia, especially near the start of the stairs. The walls are painted a faded olive-gray and are spotted with stains resembling old watermarks. A dry, heavy silence dominates the corridor, broken only by the echo of the subject’s own footsteps.\n\nLining both walls are **family photographs in wooden frames** — 18 in total, 9 on each side. The images vary: elderly couples, parents with children, group portraits taken in unidentifiable interiors. The faces are smiling, but the smiles feel tense — **too wide, too strained**. All photos are rendered in a flat yellow hue, as if viewed through an old filter or faded film. The individuals in the photographs remain unidentified — all attempts at recognition have failed. Some subjects have claimed the faces feel familiar but are unable to recall any names or details.\n\nAccording to SIT records, the photographs exhibit **anomalous resilience**: they cannot be broken or burned. Attempts to photograph or replicate the images result in distortions — the faces blur, and the backgrounds disappear.\n\n**Transition to the Staircase:**  \nAt the end of the hallway, the subject encounters a **steep staircase made of aged wood**, seamlessly embedded into the room’s structure. It contains 12 steps, ascending sharply at a ~50° angle. There are no handrails. The boards creak underfoot but do not bend or break, regardless of weight. Upon stepping on the third step, a **barely audible click** is heard — its source remains unidentified, and no equipment has recorded any unusual sound at that moment.\n\nTo the left, above the sixth step, hangs a **metal plaque** with a handwritten message in marker:  \n**“Have you been here before?”**  \nObservers claim this message **was not present previously**.\n\n**The Upper Door:**  \nAt the top of the stairs is a **heavy door. Attached to it is a **SIT-issued warning poster**:\n\n> ### [[PHOTOGRAPH OF THE POSTER]]\n\n**Notes:**\n\n- BP-429-1 is the **first recorded room** by SIT that leads to the **second floor** of Voeemsog.\n    \n- All photographs have been confiscated and are now property of SIT.	161cfb09-a5d8-416f-94f1-f51bf128e03b
afc6d11c-8ae4-42e7-86f3-de427ede0bd2	ru_RU	Кошмарные муки	123	f5574f7c-8661-4981-a34c-059fc2c56a6c
\.


--
-- Data for Name: rooms; Type: TABLE DATA; Schema: public; Owner: sitemployee
--

COPY public.rooms (id, code, floor, danger_type, size, color, light_level) FROM stdin;
5d09d9c9-ba45-44ea-a3b9-7a8be0c83484	AK-610	1	5	3x4	RED	1
161cfb09-a5d8-416f-94f1-f51bf128e03b	BP-429	1	0	2x1	BLUE	3
f5574f7c-8661-4981-a34c-059fc2c56a6c	BU-614	1	5	3x1	BLUE	0
\.


--
-- Name: room_translations room_translations_pkey; Type: CONSTRAINT; Schema: public; Owner: sitemployee
--

ALTER TABLE ONLY public.room_translations
    ADD CONSTRAINT room_translations_pkey PRIMARY KEY (id);


--
-- Name: rooms rooms_pkey; Type: CONSTRAINT; Schema: public; Owner: sitemployee
--

ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (id);


--
-- Name: idx_room_code; Type: INDEX; Schema: public; Owner: sitemployee
--

CREATE INDEX idx_room_code ON public.rooms USING btree (code);


--
-- Name: idx_unique_translation; Type: INDEX; Schema: public; Owner: sitemployee
--

CREATE UNIQUE INDEX idx_unique_translation ON public.room_translations USING btree (room_id, lang);


--
-- Name: room_translations fk_room; Type: FK CONSTRAINT; Schema: public; Owner: sitemployee
--

ALTER TABLE ONLY public.room_translations
    ADD CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES public.rooms(id) ON DELETE CASCADE;


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON SEQUENCES TO sitemployee;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON TABLES TO sitemployee;


--
-- PostgreSQL database dump complete
--

