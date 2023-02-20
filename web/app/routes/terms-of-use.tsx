import { Button, Typography } from "antd";
import RowLayout from "~/components/RowLayout";

export default function TermsOfUse() {
  return (
    <RowLayout className="app-term-of-use">
      <Typography.Title level={2} className="app-term-of-use-title">Conditions générales d’utilisation du site</Typography.Title>


      <Typography.Title level={4} className="app-term-of-use-subtitle">Article 1 : Objet</Typography.Title>
      <Typography.Paragraph>
        Les présentes CGU ou Conditions Générales d’Utilisation encadrent juridiquement l’utilisation des services du site lapetiteperle (ci-après dénommé « le site »).
      </Typography.Paragraph>
      <Typography.Paragraph>
        Constituant le contrat entre la société La Petite Perle, l’Utilisateur, l’accès au site doit être précédé de l’acceptation de ces CGU. L’accès à cette plateforme signifie l’acceptation des présentes CGU.
      </Typography.Paragraph>


      <Typography.Title level={4} className="app-term-of-use-subtitle">Article 2 : Mentions légales</Typography.Title>
      <Typography.Paragraph>
        L’édition du site lapetiteperle est assurée par la société La Petite Perle inscrite au RCS sous le numéro 451 432 228, dont le siège social est localisé au 3 Rue Docteur Joubert, 59110, La Madeleine, France Métropolitaine.         </Typography.Paragraph>
      <Typography.Paragraph>
        L’hébergeur du site lapetiteperle.fr est la société LHW, sise au 5 Rue Keller, 59100 Roubaix, France.
      </Typography.Paragraph>



      <Typography.Title level={4} className="app-term-of-use-subtitle">Article 3 : Accès au site</Typography.Title>
      <Typography.Paragraph>
        Le site est accessible gratuitement depuis n’importe où par tout utilisateur disposant d’un accès à Internet. Tous les frais nécessaires pour l’accès aux services (matériel informatique, connexion Internet…) sont à la charge de l’utilisateur.
      </Typography.Paragraph>
      <Typography.Paragraph>
        L’accès aux services dédiés aux membres s’effectue à l’aide d’un identifiant et d’un mot de passe.
      </Typography.Paragraph>



      <Typography.Title level={4} className="app-term-of-use-subtitle">Article 4 : Collecte des données</Typography.Title>
      <Typography.Paragraph>
        Pour la création du compte de l’Utilisateur, la collecte des informations au moment de l’inscription sur le site est nécessaire et obligatoire. Conformément à la loi n°78-17 du 6 janvier relative à l’informatique, aux fichiers et aux libertés, la collecte et le traitement d’informations personnelles s’effectuent dans le respect de la vie privée.
      </Typography.Paragraph>
      <Typography.Paragraph>
        Suivant la loi Informatique et Libertés en date du 6 janvier 1978, articles 39 et 40, l’Utilisateur dispose du droit d’accéder, de rectifier, de supprimer et d’opposer ses données personnelles. L’exercice de ce droit s’effectue par :
      </Typography.Paragraph>



      <Typography.Title level={4} className="app-term-of-use-subtitle"> Article 5 : Propriété intellectuelle</Typography.Title>
      <Typography.Paragraph>
        Les marques, logos ainsi que les contenus du site lapetiteperle (illustrations graphiques, textes…) sont protégés par le Code de la propriété intellectuelle et par le droit d’auteur.

      </Typography.Paragraph>
      <Typography.Paragraph>
        La reproduction et la copie des contenus par l’Utilisateur requièrent une autorisation préalable du site. Dans ce cas, toute utilisation à des usages commerciaux ou à des fins publicitaires est proscrite.

      </Typography.Paragraph>


      <Typography.Title level={4} className="app-term-of-use-subtitle">Article 6 : Responsabilité</Typography.Title>
      <Typography.Paragraph>
        Bien que les informations publiées sur le site soient réputées fiables, le site se réserve la faculté d’une non-garantie de la fiabilité des sources.

      </Typography.Paragraph>
      <Typography.Paragraph>
        Les informations diffusées sur le site lapetiteperle sont présentées à titre purement informatif et sont sans valeur contractuelle. En dépit des mises à jour régulières, la responsabilité du site ne peut être engagée en cas de modification des dispositions administratives et juridiques apparaissant après la publication. Il en est de même pour l’utilisation et l’interprétation des informations communiquées sur la plateforme.

      </Typography.Paragraph>



      <Typography.Title level={4} className="app-term-of-use-subtitle">Article 7 : Liens hypertextes</Typography.Title>
      <Typography.Paragraph>
        Le site peut être constitué de liens hypertextes. En cliquant sur ces derniers, l’Utilisateur sortira de la plateforme. Cette dernière n’a pas de contrôle et ne peut pas être tenue responsable du contenu des pages web relatives à ces liens.
      </Typography.Paragraph>

      <Button type="primary" href='/' className="app-term-of-use-back">Retourner à la page d'accueil</Button>


    </RowLayout>
  );
}
