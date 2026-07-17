(ns culture.facts
  "Regional-culture catalog for Washington, D.C. -- local dishes,
  protected products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"washington-dc"
   [{:culture/id "washington-dc.dish.half-smoke"
     :culture/name "Half-smoke"
     :culture/municipality "washington-dc"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Type of hot dog found in Washington, D.C. and the surrounding region, larger, spicier and more coarsely ground than a regular hot dog; a fixture at Ben's Chili Bowl."
     :culture/url "https://en.wikipedia.org/wiki/Half-smoke"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "washington-dc.dish.senate-bean-soup"
     :culture/name "Senate bean soup"
     :culture/municipality "washington-dc"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Soup of navy beans, ham hocks and onion served every day in the dining room of the United States Senate in Washington, D.C., a tradition dating to the early 20th century."
     :culture/url "https://en.wikipedia.org/wiki/Senate_bean_soup"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "washington-dc.dish.jumbo-slice"
     :culture/name "Jumbo slice"
     :culture/municipality "washington-dc"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Oversized New York-style pizza sold by the slice to go, especially popular in the Adams Morgan neighborhood of Washington, D.C."
     :culture/url "https://en.wikipedia.org/wiki/Jumbo_slice"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "washington-dc.product.mumbo-sauce"
     :culture/name "Mumbo sauce"
     :culture/municipality "washington-dc"
     :culture/country "USA"
     :culture/kind :product
     :culture/summary "Takeout condiment specialty of Washington, D.C., resembling barbecue sauce but sweeter and spicier or more sour, commonly served on fried chicken wings, french fries and fried rice."
     :culture/url "https://en.wikipedia.org/wiki/Mumbo_sauce"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "washington-dc.beverage.rickey"
     :culture/name "Rickey"
     :culture/municipality "washington-dc"
     :culture/country "USA"
     :culture/kind :beverage
     :culture/summary "Highball of gin or bourbon, lime juice and carbonated water, created at Shoomaker's bar in Washington, D.C. in the 1880s and designated the District's official native cocktail in 2011."
     :culture/url "https://en.wikipedia.org/wiki/Rickey_(cocktail)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "washington-dc.festival.national-cherry-blossom-festival"
     :culture/name "National Cherry Blossom Festival"
     :culture/municipality "washington-dc"
     :culture/country "USA"
     :culture/kind :festival
     :culture/summary "Spring celebration in Washington, D.C. commemorating the 1912 gift of Japanese cherry trees from the mayor of Tokyo, celebrating friendship between the United States and Japan."
     :culture/url "https://en.wikipedia.org/wiki/National_Cherry_Blossom_Festival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "washington-dc.festival.smithsonian-folklife-festival"
     :culture/name "Smithsonian Folklife Festival"
     :culture/municipality "washington-dc"
     :culture/country "USA"
     :culture/kind :festival
     :culture/summary "International exhibition of living cultural heritage presented annually in the summer on the National Mall in Washington, D.C., for two weeks around the Fourth of July."
     :culture/url "https://en.wikipedia.org/wiki/Smithsonian_Folklife_Festival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "washington-dc.heritage.national-mall"
     :culture/name "National Mall"
     :culture/municipality "washington-dc"
     :culture/country "USA"
     :culture/kind :heritage
     :culture/summary "Landscaped park near downtown Washington, D.C. containing Smithsonian museums, memorials and cultural institutions, extending from the U.S. Capitol to the Lincoln Memorial."
     :culture/url "https://en.wikipedia.org/wiki/National_Mall"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "washington-dc.heritage.lincoln-memorial"
     :culture/name "Lincoln Memorial"
     :culture/municipality "washington-dc"
     :culture/country "USA"
     :culture/kind :heritage
     :culture/summary "U.S. national memorial honoring Abraham Lincoln, located on the western end of the National Mall in Washington, D.C."
     :culture/url "https://en.wikipedia.org/wiki/Lincoln_Memorial"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-usa-washington-dc culture catalog "
                 "(ADR-2607171400): " (count (get catalog "washington-dc"))
                 " Washington, D.C. entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
