(ns ordinance.facts
  "Municipal-code compliance catalog for Washington, D.C. -- the SECOND
  municipality-level entry (see cloud-itonami-municipality-jpn-tokyo for
  the first) per ADR-2607141700 (cloud-itonami-compliance-fact-federation).
  This was the originally-planned Wave 0 second municipality
  (JPN/Tokyo + USA/Washington D.C.), delivered after several ticks
  focused on the country/association axes per the owner's repeated
  emphasis (\"国, 団体\") -- the municipality axis had been flagged as the
  thinnest axis for many consecutive ticks.

  Every entry cites an OFFICIAL code.dccouncil.gov (D.C. Law Library)
  URL -- never fabricated. An entry not in this table has NO spec-basis,
  full stop; extend `catalog`, do not invent an id/url. Both entries
  below were directly WebFetch-verified against the live
  code.dccouncil.gov page on 2026-07-15 (unlike several US state-level
  government portals attempted in earlier ticks, this DC council site
  rendered cleanly).")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"washington-dc"
   [{:ordinance/id "washington-dc.freedom-of-information-act"
     :ordinance/title "Freedom of Information Act (D.C. Official Code Title 2, Chapter 5, Subchapter II)"
     :ordinance/municipality "washington-dc"
     :ordinance/country "USA"
     :ordinance/kind :municipal-code
     :ordinance/url "https://code.dccouncil.gov/us/dc/council/code/titles/2/chapters/5/subchapters/II"
     :ordinance/url-provenance :official-dc-law-library
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:information-disclosure :transparency}}
    {:ordinance/id "washington-dc.human-rights-act"
     :ordinance/title "Human Rights Law (D.C. Official Code Title 2, Chapter 14, Unit A)"
     :ordinance/municipality "washington-dc"
     :ordinance/country "USA"
     :ordinance/kind :municipal-code
     :ordinance/url "https://code.dccouncil.gov/us/dc/council/code/titles/2/chapters/14/units/A"
     :ordinance/url-provenance :official-dc-law-library
     :ordinance/enacted-date "1977-12-13"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:anti-discrimination :human-rights}}]})

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
      :note (str "cloud-itonami-municipality-usa-washington-dc Wave 0 (ADR-2607141700): "
                 (count (get catalog "washington-dc")) " Washington D.C. entries seeded "
                 "with an official code.dccouncil.gov citation. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
