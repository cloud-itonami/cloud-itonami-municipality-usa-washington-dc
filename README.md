# cloud-itonami-municipality-usa-washington-dc

Municipal-code compliance catalog for **Washington, D.C.** — the SECOND
municipality-level entry alongside
[`cloud-itonami-municipality-jpn-tokyo`](https://github.com/cloud-itonami/cloud-itonami-municipality-jpn-tokyo).
Part of the [`cloud-itonami`](https://github.com/cloud-itonami)
compliance-fact family (ADR-2607141700,
`cloud-itonami-compliance-fact-federation`, in `com-junkawasaki/root`).

This was the originally-planned Wave 0 second municipality
(JPN/Tokyo + USA/Washington D.C.), delivered after the country and
industry-association axes had grown to 8 and 9 entries respectively —
the municipality axis had been the clear outlier for many ticks.

## Scope

A **read-only reference/archive** catalog — not an Advisor⊣Governor
actuation actor. It proposes or executes nothing on the District of
Columbia's behalf.

Coverage is reported honestly (see `ordinance.facts/coverage`): a
municipality not in `catalog` has **no spec-basis**, full stop — never
fabricate one.

## Data

- `src/ordinance/facts.cljc` — the catalog, source of truth.
- `schema/ordinance.edn` — DataScript schema.
- `data/datascript-tx.edn` — derived DataScript tx-data (query this
  alongside other `cloud-itonami`/`etzhayyim` compliance-fact sources via
  `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`).

Every entry cites an official
[code.dccouncil.gov](https://code.dccouncil.gov/) (D.C. Law Library)
page, directly WebFetch-verified (2026-07-15).

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention). Code text itself
remains the District of Columbia's; this repo stores only citation
metadata (id/title/url/dates), not full text.
